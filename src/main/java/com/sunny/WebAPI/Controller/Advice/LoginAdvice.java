package com.sunny.WebAPI.Controller.Advice;

import com.sunny.LogIn.Ex.NoClientSessionException;
import com.sunny.LogIn.Ex.NoPermissionToConnectException;
import com.sunny.LogIn.Ex.TokenExpiredException;
import com.sunny.Service.Ex.LoginException.InvalidLoginException;
import com.sunny.WebAPI.Controller.LoginController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackageClasses = LoginController.class)
@ResponseBody
public class LoginAdvice {

    @ExceptionHandler(NoClientSessionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNoClientSessionException(NoClientSessionException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NoPermissionToConnectException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleNoPermissionToConnectException(NoPermissionToConnectException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleTokenExpiredException(TokenExpiredException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleInvalidLoginException(InvalidLoginException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

}
