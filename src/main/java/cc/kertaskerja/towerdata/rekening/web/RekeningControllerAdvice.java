package cc.kertaskerja.towerdata.rekening.web;

import cc.kertaskerja.towerdata.rekening.domain.exception.RekeningAlreadyExistException;
import cc.kertaskerja.towerdata.rekening.domain.exception.RekeningNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RekeningControllerAdvice {
    @ExceptionHandler(RekeningNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String rekeningNotFoundHandler(RekeningNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RekeningAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String rekeningAlreadyExistHandler(RekeningAlreadyExistException ex) {
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
