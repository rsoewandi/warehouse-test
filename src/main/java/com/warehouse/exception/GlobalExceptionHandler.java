package com.warehouse.exception;

import com.warehouse.response.ApiResponse;
import com.warehouse.util.ResponseUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error(ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseUtil.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseUtil.error("Unexpected error: " + ex.getMessage()));
    }
}
