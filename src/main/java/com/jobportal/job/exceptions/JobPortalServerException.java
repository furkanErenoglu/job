package com.jobportal.job.exceptions;

import org.springframework.http.HttpStatus;

public class JobPortalServerException extends RuntimeException {
    private static final long serialVersionUID = 62348743L;

    private HttpStatus httpStatusCode;

    public JobPortalServerException(){
        super("Unexpected Exception encountered.");
    }

    public JobPortalServerException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatusCode = httpStatus;
    }
}
