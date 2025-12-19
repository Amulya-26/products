package dev.amulya.productservice.advice;

import dev.amulya.productservice.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException(NullPointerException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("The requested product was not found.");
        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(400));
    }
}
