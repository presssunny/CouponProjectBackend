package com.sunny.Mapper;

import com.sunny.Data.Entity.Company;
import com.sunny.Data.Entity.Coupon;
import com.sunny.Data.Entity.Customer;
import com.sunny.WebAPI.Dto.CompanyDto;
import com.sunny.WebAPI.Dto.CouponDto;
import com.sunny.WebAPI.Dto.CustomerDto;

public interface ProjectMapper {

    Company map(CompanyDto dto);
    CompanyDto mapToDto(Company entity);
    Coupon map(CouponDto dto);
    CouponDto mapToDto (Coupon entity);
    Customer map(CustomerDto dto);
    CustomerDto mapToDto(Customer entity);
}
