package com.juan.escuela.dto;

import java.util.HashMap;
import java.util.Map;

public class ErrorDto {
    private Map<String,String> error;

    public ErrorDto() {
    }

    public ErrorDto(Map<String, String> error) {
        this.error = error;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    public void add(String key, String value) {
        if (this.error == null) {
            this.error = new HashMap<>();
        }

        error.put(key, value);
    }

    public static class Builder {

    }

}
