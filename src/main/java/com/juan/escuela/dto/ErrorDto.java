package com.juan.escuela.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {
    private Map<String,String> error;

    public void add(String key, String value) {
        if (this.error == null) {
            this.error = new HashMap<>();
        }

        error.put(key, value);
    }

}
