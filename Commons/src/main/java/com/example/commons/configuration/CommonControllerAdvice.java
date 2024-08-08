package com.example.commons.configuration;

import com.example.commons.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;

@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false), request.getHeader(CORRELATION_ID));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
