package com.example.cadastropet.Exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.cadastropet.Response.ExceptionMessage;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= AgeHigherThan19.class)
    public ResponseEntity<Object> AgeHigherThan19(Exception e, WebRequest request){
        String exception = e.getClass().getSimpleName();
        String exceptionDescription =e.getLocalizedMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), exceptionDescription, exception);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
