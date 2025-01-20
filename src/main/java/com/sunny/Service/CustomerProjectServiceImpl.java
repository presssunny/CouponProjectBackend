package com.sunny.Service;

import com.sunny.Data.Entity.Coupon;
import com.sunny.Data.Entity.Customer;
import com.sunny.Data.Repository.CouponRepository;
import com.sunny.Data.Repository.CustomerRepository;
import com.sunny.Mapper.ProjectMapper;
import com.sunny.Service.Ex.CustomerException.*;
import com.sunny.WebAPI.Dto.CouponDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
// TODO: 03/08/2023 checkout the above
public class
CustomerProjectServiceImpl implements CustomerProjectService {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final ProjectMapper mapper;

    @Override
    public Set<CouponDto> getPurchasedCoupon(UUID customerUuid) {
        Optional<Customer> customerOptional = customerRepository.findByUuid(customerUuid);

        if (customerOptional.isEmpty()) {
            throw new NoSuchCustomerException("Sorry, there is not such customer in the system");
        }

        Set<Coupon> purchasedCoupons = couponRepository.findPurchasedCouponsByCustomer(customerUuid);

        if (purchasedCoupons.isEmpty()) {
            throw new NoPurchasedCouponsForThisCustomerException("Sorry, but the customer did not buy yet any coupons");
        }

        return purchasedCoupons.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toSet());
    }

    @Override

    public Set<CouponDto> getUnPurchasedCoupon(UUID customerUuid) {
        Optional<Customer> customerOptional = customerRepository.findByUuid(customerUuid);

        if (customerOptional.isEmpty()) {
            throw new NoSuchCustomerException("Sorry, there is not such customer in the system");
        }

        Set<Coupon> purchasedCoupons = couponRepository.findPurchasedCouponsByCustomer(customerUuid);

        Set<Coupon> allCoupons =new HashSet<>(couponRepository.findAll());
        allCoupons.removeAll(purchasedCoupons);

        if (allCoupons.isEmpty()) {
            throw new TheCustomerBoughtAllCouponsException("Sorry, but the customer buy already all the coupons in the system");
        }

        return allCoupons.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CouponDto> getExpiringCoupons(UUID customerUuid) {
        Optional<Customer> customerOptional = customerRepository.findByUuid(customerUuid);

        if (customerOptional.isEmpty()) {
            throw new NoSuchCustomerException("Sorry, there is not such customer in the system");
        }

        Customer customer = customerOptional.get();

        LocalDate weekAhead =  LocalDate.now().plusWeeks(1);

        Set<Coupon> purchasedCoupons = customer.getPurchasedCoupons();

        Set<Coupon> expiringCoupons = purchasedCoupons.stream()
                .filter(coupon -> coupon.getEndDate().isBefore(weekAhead))
                .collect(Collectors.toSet());

        if (expiringCoupons.isEmpty()) {
            return Collections.emptySet();
        }

        return expiringCoupons.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<CouponDto> purchaseCoupon(UUID customerUuid, UUID couponUuid) {
        Optional<Customer> customerOptional = customerRepository.findByUuid(customerUuid);
        Optional<Coupon> couponOptional = couponRepository.findByUuid(couponUuid);

        if (customerOptional.isEmpty()) {
            throw new NoSuchCustomerException("Sorry, there is not such customer in the system");
        }

        if (couponOptional.isEmpty()) {
            throw new NoSuchCouponException("Sorry, there is not such coupon in the system");
        }

        Customer customer = customerOptional.get();
        Coupon coupon = couponOptional.get();

        Set<Coupon> customerCoupons = customer.getPurchasedCoupons();
        if (customerCoupons.contains(coupon)) {
            throw new TheCustomerHasAlreadyPurchasedThisCouponBeforeException(
                    "Sorry but the customer already buy this coupon before");
        }

        if (coupon.getAmount() <= 0) {
            throw new CouponOutOfStockException("Sorry, but the coupon is out of stock");
        }

        coupon.setAmount(coupon.getAmount() - 1);
        customerCoupons.add(coupon);

        customerRepository.save(customer);
        couponRepository.save(coupon);

        return Optional.of(mapper.mapToDto(coupon));

    }
}
