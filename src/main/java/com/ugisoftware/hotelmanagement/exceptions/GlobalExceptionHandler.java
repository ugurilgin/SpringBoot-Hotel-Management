package com.ugisoftware.hotelmanagement.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Exception Handlers Here

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex){
    	 List<String> details = new ArrayList<>();
    	 details.add(ex.getMessage());
        ApiError apiError = new ApiError.
                Builder()
                .withMessage(details)
                .withHttpStatus(HttpStatus.NOT_FOUND)
                .withCreatedAt()
                .build();

        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }
    // Generalized Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(MethodArgumentNotValidException  ex){
    	 List<String> details = new ArrayList<>();
         for(ObjectError error : ex.getBindingResult().getAllErrors()) {
             details.add(error.getDefaultMessage());
         }
        ApiError apiError = new ApiError.
                Builder()
                
                .withMessage(details)
                .withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withCreatedAt()
                .build();

        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }

}