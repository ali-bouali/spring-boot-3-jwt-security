package com.herookie.employee.configurations;

import java.util.Date;

import com.herookie.employee.exceptions.EntityNotFoundException;
import com.herookie.employee.exceptions.ErrorProcessingException;
import com.herookie.employee.exceptions.UnsavedEntityException;
import com.herookie.employee.models.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name()).timestamp(new Date()).message(ex.getMessage())
                .path(request.getDescription(false)).build();
        this.printError(request, ex);
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnsavedEntityException.class)
    public ResponseEntity<ErrorMessage> unsavedEntityException(UnsavedEntityException ex, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.NOT_FOUND.name()).timestamp(new Date()).message(ex.getMessage())
                .path(request.getDescription(false)).build();
        this.printError(request, ex);
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorProcessingException.class)
    public ResponseEntity<ErrorMessage> errorProcessingException(ErrorProcessingException ex, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name()).timestamp(new Date()).message(ex.getMessage())
                .path(request.getDescription(false)).build();
        this.printError(request, ex);
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex,
            WebRequest request) {
        StringBuilder messages = new StringBuilder();
        for (int i = 0; i < ex.getBindingResult().getAllErrors().size(); i++) {
            ObjectError error = ex.getBindingResult().getAllErrors().get(i);
            String errorMessage = error.getDefaultMessage();
            if (ex.getBindingResult().getAllErrors().size() == (i + 1)) {
                messages.append(errorMessage);
            } else {
                messages.append(errorMessage + ",");
            }
        }
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name()).timestamp(new Date()).message(messages.toString())
                .path(request.getDescription(false)).build();
        this.printError(request, ex, message.getMessage());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name()).timestamp(new Date()).message(ex.getMessage())
                .path(request.getDescription(false)).build();
        this.printError(request, ex);
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public void printError(WebRequest request, Exception e, String... message) {
        try {
            log.error("CONTROLLER"
                    + request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler", 0));
            log.error("PATH" + request.getDescription(false));
            if (request.getParameterMap().keySet().size() > 0) {
                for (String key : request.getParameterMap().keySet()) {
                    log.error("PARAM" + key + "=" + request.getParameterMap().get(key)[0]);
                }
            }
            for (String string : request.getAttributeNames(1)) {
                log.info(string);
            }
            if (message != null && message.length > 0 && message[0] != null) {
                log.error("MESSAGE" + message[0]);
            } else {
                log.error("MESSAGE" + e.getMessage());
            }
            log.error("CAUSE" + e.getCause());
        } catch (Exception ex) {
            log.error(e.getMessage(), ex);
        }
    }
}