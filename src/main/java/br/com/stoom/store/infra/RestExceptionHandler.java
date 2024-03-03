package br.com.stoom.store.infra;

import br.com.stoom.store.exceptions.BrandNotFoundException;
import br.com.stoom.store.exceptions.CategoryAlreadyExistsException;
import br.com.stoom.store.exceptions.CategoryNotFoundException;
import br.com.stoom.store.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BrandNotFoundException.class)
    private ResponseEntity<RestErrorMessage> brandNotFound(BrandNotFoundException brandNotFoundException){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,brandNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    private ResponseEntity<RestErrorMessage> categoryNotFound(CategoryNotFoundException categoryNotFoundException){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,categoryNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> categoryAlreadyExists(CategoryAlreadyExistsException categoryAlreadyExistsException){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT,categoryAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<RestErrorMessage> productNotExists(ProductNotFoundException productNotFoundException){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,productNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

}
