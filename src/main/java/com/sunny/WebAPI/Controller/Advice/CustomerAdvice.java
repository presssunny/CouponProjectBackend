package com.sunny.WebAPI.Controller.Advice;

import com.sunny.Service.Ex.CustomerException.*;
import com.sunny.WebAPI.Controller.CustomerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackageClasses = CustomerController.class)
@ResponseBody
public class CustomerAdvice {

    @ExceptionHandler(NoSuchCustomerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNoSuchCustomerException(NoSuchCustomerException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(NoSuchCouponException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNoSuchCouponException(NoSuchCouponException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NoPurchasedCouponsForThisCustomerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNoPurchasedCouponsForThisCustomerException(NoPurchasedCouponsForThisCustomerException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(TheCustomerBoughtAllCouponsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleTheCustomerBoughtAllCouponsException(TheCustomerBoughtAllCouponsException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CouponOutOfStockException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleCouponOutOfStockException(CouponOutOfStockException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(TheCustomerHasAlreadyPurchasedThisCouponBeforeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleTheCustomerHasAlreadyPurchasedThisCouponBeforeException(TheCustomerHasAlreadyPurchasedThisCouponBeforeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
