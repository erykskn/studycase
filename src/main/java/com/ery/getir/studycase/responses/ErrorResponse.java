package com.ery.getir.studycase.responses;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private Map<String, List<String>> errors;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }
}
