package br.com.acme.application.handler;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.acme.application.service.exception.BusinessException;
import br.com.acme.application.service.exception.InformationNotFoundException;
import br.com.acme.infrastructure.service.MessageService;
import br.com.acme.presentation.dto.shared.ResponseTO;

@ControllerAdvice
public class ApplicationResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);

        return handleException(exception, HttpStatus.BAD_REQUEST, request, "resource.invalid-operation");
    }

    @ExceptionHandler({ InformationNotFoundException.class })
    public ResponseEntity<Object> handleInformationNotFoundException(InformationNotFoundException exception, WebRequest request) {
        return handleException(exception, HttpStatus.NOT_FOUND, request, "resource.information-not-found");
    }

    @ExceptionHandler({ BusinessException.class })
    public ResponseEntity<Object> handleBusinessException(BusinessException exception, WebRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    protected ResponseEntity<Object> handleException(Exception exception, HttpStatus status, WebRequest request, String key) {
        ResponseTO<List<String>> response = new ResponseTO<>(Arrays.asList((messageService.getMessage(key))));

        return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
    }

}
