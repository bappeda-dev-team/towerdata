package cc.kertaskerja.towerdata.jabatanpegawai.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cc.kertaskerja.towerdata.jabatanpegawai.domain.exception.JabatanPegawaiAlreadyExistException;
import cc.kertaskerja.towerdata.jabatanpegawai.domain.exception.JabatanPegawaiNotFoundException;

@RestControllerAdvice
public class JabatanPegawaiControllerAdvice {
	@ExceptionHandler(JabatanPegawaiNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String jabatanPegawaiNotFoundHandler(JabatanPegawaiNotFoundException ex) {
        return ex.getMessage();
    }
	
	@ExceptionHandler(JabatanPegawaiAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String jabatanPegawaiAlreadyExistException(JabatanPegawaiAlreadyExistException ex) {
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
