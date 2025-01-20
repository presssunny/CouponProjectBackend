package com.sunny.Service.Ex.CompanyException;

public class CouponNotBelongToCompanyException extends RuntimeException {
    public CouponNotBelongToCompanyException(String message) {
        super(message);
    }
}
