package com.wego.parkingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ParkingExceptionHandler  extends  RuntimeException {

    public ParkingExceptionHandler(String exception){
        super(exception);

    }
}
