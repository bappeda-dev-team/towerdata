package cc.kertaskerja.towerdata.urusan.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cc.kertaskerja.towerdata.urusan.domain.exception.UrusanAlreadyExistException;
import cc.kertaskerja.towerdata.urusan.domain.exception.UrusanNotFoundException;

@RestControllerAdvice
public class UrusanControllerAdvice {
	@ExceptionHandler(UrusanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urusanNotFoundHandler(UrusanNotFoundException ex) {
        return ex.getMessage();
    }
	
	@ExceptionHandler(UrusanAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urusanAlreadyExistHandler(UrusanAlreadyExistException ex) {
        return ex.getMessage();
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String errMessage = err.getDefaultMessage();
            errors.put(fieldName, errMessage);
        });

        return errors;
    }
}
