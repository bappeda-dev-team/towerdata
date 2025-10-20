package cc.kertaskerja.towerdata.bidangurusan.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cc.kertaskerja.towerdata.bidangurusan.domain.exception.BidangUrusanAlreadyExistException;
import cc.kertaskerja.towerdata.bidangurusan.domain.exception.BidangUrusanNotFoundException;

@RestControllerAdvice
public class BidangUrusanControllerAdvice {
	@ExceptionHandler(BidangUrusanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bidangUrusanNotFoundHandler(BidangUrusanNotFoundException ex) {
        return ex.getMessage();
    }
	
	@ExceptionHandler(BidangUrusanAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bidangUrusanAlreadyExistException(BidangUrusanAlreadyExistException ex) {
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
