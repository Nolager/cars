package cl.andres.cars.exception;

import cl.andres.cars.dto.response.ExceptionResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.info("Not found exception caught ", ex);
        return new ResponseEntity<>(new ExceptionResponseDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
