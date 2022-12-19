package edu.bbte.idde.vlim2099.spring.controller.dto.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.stream.Stream;

@Slf4j
@ControllerAdvice
public class ValidationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Stream<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Invalid dto {}", exception);
        return exception.getBindingResult().getFieldErrors().stream().map((fieldError) ->
                fieldError.getField() + " " + fieldError.getDefaultMessage());
    }
}
