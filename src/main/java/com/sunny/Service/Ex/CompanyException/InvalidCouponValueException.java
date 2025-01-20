package com.sunny.Service.Ex.CompanyException;

public class InvalidCouponValueException extends RuntimeException {
    public InvalidCouponValueException(String message) {
        super(message);
    }
}
