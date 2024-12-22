package com.assignment.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
   public ResponseEntity<ErrorDetail> handleUserAlreadyExistsException(UserAlreadyExistsException e, WebRequest req){
       ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),req.getDescription(false), LocalDateTime.now());
       return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
   }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleUserNotFoundException(UserNotFoundException e, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorDetail> handleAccessDeniedException(AccessDeniedException e, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGenericException(Exception e, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),req.getDescription(false), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
    }

}
