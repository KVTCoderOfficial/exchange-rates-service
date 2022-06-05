package ru.kvt.exchangeratesservice.controllers.handlers;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.kvt.exchangeratesservice.dto.errors.ErrorDto;
import ru.kvt.exchangeratesservice.dto.errors.ErrorMessage;

@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        log.error(exception.getMessage());

        if (exception instanceof MissingServletRequestParameterException)
            return process(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_REQUEST_PARAMS);

        if (exception instanceof MethodArgumentNotValidException)
            return process(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_REQUEST_PARAMS);

        if (exception instanceof MethodArgumentTypeMismatchException)
            return process(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_REQUEST_PARAMS);

        if (exception instanceof HttpMessageNotReadableException)
            return process(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_REQUEST_PARAMS);

        if (exception instanceof FeignException)
            return process(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INVALID_CREDENTIALS_FOR_EXTERNAL_SERVICES);

        return process(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.UNEXPECTED_ERROR);
    }

    private ResponseEntity<ErrorDto> process(HttpStatus httpStatus, ErrorMessage errorMessage) {
        ErrorDto err = new ErrorDto(httpStatus.value(), httpStatus.getReasonPhrase(), errorMessage.getMessage());
        return new ResponseEntity<>(err, httpStatus);
    }

}
