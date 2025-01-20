package com.sunny.Service.Ex.CustomerException;

public class TheCustomerHasAlreadyPurchasedThisCouponBeforeException extends RuntimeException {
    public TheCustomerHasAlreadyPurchasedThisCouponBeforeException(String message) {
        super(message);
    }
}
