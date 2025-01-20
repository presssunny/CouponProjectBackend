package com.sunny.Service.Ex.CustomerException;

public class NoSuchCouponException extends RuntimeException {
    public NoSuchCouponException(String message) {
        super(message);
    }
}
