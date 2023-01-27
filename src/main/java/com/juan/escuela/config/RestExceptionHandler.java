package com.juan.escuela.config;

import com.juan.escuela.dto.ErrorDto;
import com.juan.escuela.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handlerException(AppException ex){
    ErrorDto errorDto = new ErrorDto();
    errorDto.add("mensaje", ex.getMessage());

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(errorDto);

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handlerExceptionValid(MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();

        ex.getBindingResult().getAllErrors().forEach(err -> {
            String nombreParametro = ((FieldError) err).getField();
            String mensajeParametro = err.getDefaultMessage();

            errorDto.add(nombreParametro, mensajeParametro);
        });

        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }
    
}
