package com.sunny.Mapper;

import com.sunny.Data.Entity.Company;
import com.sunny.Data.Entity.Coupon;
import com.sunny.Data.Entity.Customer;
import com.sunny.WebAPI.Dto.CompanyDto;
import com.sunny.WebAPI.Dto.CouponDto;
import com.sunny.WebAPI.Dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component

public class ProjectMapperImpl implements ProjectMapper {
    @Override
    public Company map(CompanyDto dto) {
        return Company.builder()
                .uuid(dto.getUuid())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public CompanyDto mapToDto(Company entity) {
        return CompanyDto.builder()
                .uuid(entity.getUuid())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public Coupon map(CouponDto dto) {
        return Coupon.builder()
                .uuid(dto.getUuid())
                .category(dto.getCategory())
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .image(dto.getImage())
                .build();
    }

    @Override
    public CouponDto mapToDto(Coupon entity) {
        return CouponDto.builder()
                .uuid(entity.getUuid())
                .category(entity.getCategory())
                .title(entity.getTitle())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .amount(entity.getAmount())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .image(entity.getImage())
                .build();

    }

    @Override
    public Customer map(CustomerDto dto) {
        return Customer.builder()
                .uuid(dto.getUuid())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();

    }

    @Override
    public CustomerDto mapToDto(Customer entity) {
        return CustomerDto.builder()
                .uuid(entity.getUuid())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }
}
