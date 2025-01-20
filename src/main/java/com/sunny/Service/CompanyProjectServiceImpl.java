package com.sunny.Service;

import com.sunny.Data.Entity.Company;
import com.sunny.Data.Entity.Coupon;
import com.sunny.Data.Entity.Customer;
import com.sunny.Data.Repository.CompanyRepository;
import com.sunny.Data.Repository.CouponRepository;
import com.sunny.Mapper.ProjectMapper;
import com.sunny.Service.Ex.CompanyException.*;
import com.sunny.WebAPI.Dto.CouponDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyProjectServiceImpl implements CompanyProjectService {
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
    private final ProjectMapper mapper;

    @Override
    public CouponDto createCoupon(UUID uuid, CouponDto couponDto) {
        Optional<Coupon> existingCouponOpt = couponRepository.findByUuid(couponDto.getUuid());

        if (existingCouponOpt.isPresent()) {
            throw new CouponAlreadyExistsException("Coupon already exists in the system.");
        }

        if (couponDto.getAmount() < 0) {
            throw new InvalidCouponValueException("Coupon amount must be non-negative.");
        }

        Coupon coupon = mapper.map(couponDto);

        Company company = companyRepository.findByUuid(uuid).orElseThrow(
                () -> new NoSuchCompanyException("Sorry, there is no such company in the system.")
        );

        coupon.setCompany(company);
        couponRepository.save(coupon);

        return mapper.mapToDto(coupon);
    }

    @Override
    public void deleteCoupon(UUID uuid, UUID couponDtoUuid) {
        Company company = companyRepository.findByUuid(uuid)
                .orElseThrow(() -> new NoSuchCompanyException("Sorry, there is no such company in the system."));

        Coupon coupon = couponRepository.findByUuid(couponDtoUuid)
                .orElseThrow(() -> new CouponNotFoundException("Coupon not found with UUID: " + couponDtoUuid));

        if (!coupon.getCompany().getId().equals(company.getId())) {
            throw new CouponNotBelongToCompanyException("Coupon does not belong to the company.");
        }
        for (Customer customer : coupon.getCustomers()) {
            customer.getPurchasedCoupons().remove(coupon);
        }
        couponRepository.delete(coupon);
    }

    @Override
    @Transactional
    public CouponDto updateCouponQuantity(UUID uuid, UUID couponDtoUuid, int quantity) {
        Coupon coupon = couponRepository.findByUuid(couponDtoUuid)
                .orElseThrow(() -> new CouponNotFoundException("Coupon not found with UUID: " + couponDtoUuid));

        if (!coupon.getCompany().getUuid().equals(uuid)) {
            throw new CouponNotBelongToCompanyException("Coupon does not belong to the company.");
        }

        coupon.setAmount(quantity);

        return mapper.mapToDto(coupon);
    }

    @Override
    public Set<CouponDto> seeAllCoupon(UUID uuid) {
        Company company = companyRepository.findByUuid(uuid)
                .orElseThrow(() -> new NoSuchCompanyException("Sorry, there is no such company in the system."));

        return company.getCoupons().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toSet());
    }
}
