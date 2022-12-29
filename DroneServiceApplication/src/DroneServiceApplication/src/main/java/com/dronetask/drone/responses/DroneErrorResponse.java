package com.dronetask.drone.responses;

import org.springframework.http.HttpStatus;

public class DroneErrorResponse {
    public HttpStatus code;
    public String message;
    public Object error;

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DroneErrorResponse(HttpStatus code, String message, Object error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }
}
