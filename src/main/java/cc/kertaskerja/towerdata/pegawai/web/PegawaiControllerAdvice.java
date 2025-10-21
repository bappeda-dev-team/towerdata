package cc.kertaskerja.towerdata.pegawai.web;

import cc.kertaskerja.towerdata.opd.domain.exception.OpdAlreadyExistException;
import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;
import cc.kertaskerja.towerdata.pegawai.domain.exception.PegawaiAlreadyExistException;
import cc.kertaskerja.towerdata.pegawai.domain.exception.PegawaiNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PegawaiControllerAdvice {
    @ExceptionHandler(PegawaiNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pegawaiNotFoundHandler(PegawaiNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PegawaiAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pegawaiAlreadyExistException(PegawaiAlreadyExistException ex) {
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
