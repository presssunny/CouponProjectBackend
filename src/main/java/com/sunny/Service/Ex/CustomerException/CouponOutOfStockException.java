package com.sunny.Service.Ex.CustomerException;

public class CouponOutOfStockException extends RuntimeException {
    public CouponOutOfStockException(String message) {
        super(message);
    }
}
