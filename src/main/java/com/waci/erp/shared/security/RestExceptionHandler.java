package com.waci.erp.shared.security;

import com.waci.erp.shared.api.BaseResponse;
import com.waci.erp.shared.exceptions.ValidationFailedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures that follow
 * Tugende standard response formats.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //Handles invalid request body exception
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new BaseResponse(ex.getMessage(),false),HttpStatus.BAD_REQUEST);
    }

    //Handles ValidationFailedException
    @ExceptionHandler(ValidationFailedException.class)
    protected ResponseEntity<Object> handleValidationFailedException(ValidationFailedException exception) {
        exception.printStackTrace();
        return buildResponseEntity(new BaseResponse(exception.getMessage(),false),HttpStatus.BAD_REQUEST);
    }

    //Handles uncaught exceptions
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGeneralException(Exception exception) {
        exception.printStackTrace();
        return buildResponseEntity(new BaseResponse(exception.getMessage(),false),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Maps exception to response entity
    private ResponseEntity<Object> buildResponseEntity(BaseResponse responseObject,HttpStatus status) {
        return new ResponseEntity<>(responseObject, status);
    }

}
