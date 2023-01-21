package com.dronetask.drone;

import com.dronetask.drone.exceptions.DroneException;
import com.dronetask.drone.responses.DroneErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DroneControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DroneException.class)
    public DroneErrorResponse handleInvalidArgument(DroneException e) {
        return new DroneErrorResponse(HttpStatus.BAD_REQUEST,"invalid request", e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public DroneErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> map = new HashMap<>();
        e.getConstraintViolations().forEach(constraintViolation -> {
            map.put(constraintViolation.getInvalidValue().toString(), constraintViolation.getMessage());
        });
        return new DroneErrorResponse(HttpStatus.BAD_REQUEST,"invalid request", map);
    }
}
