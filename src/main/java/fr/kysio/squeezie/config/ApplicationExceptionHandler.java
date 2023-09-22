package fr.kysio.squeezie.config;

import fr.kysio.squeezie.exceptions.ApplicationException;
import fr.kysio.squeezie.logic.dtos.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object>handleApplicationException(final ApplicationException ex, final HttpServletRequest request) {
        String guid = UUID.randomUUID().toString();
        log.error("ApplicationException: {} - {} - {}", guid, ex.getErrorCode(), ex.getMessage());

        ApiErrorDto response = new ApiErrorDto(
                guid,
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getHttpStatus().value(),
                ex.getHttpStatus().name(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

}
