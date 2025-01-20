package com.sunny.Service.Ex.CustomerException;

public class NoPurchasedCouponsForThisCustomerException extends RuntimeException {
    public NoPurchasedCouponsForThisCustomerException(String message) {
        super(message);
    }
}
