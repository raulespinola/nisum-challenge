package org.nisum.challenge.exception;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Environment environment;

    private List<String> errores;

    ResponseExceptionHandler() {
        this.errores = new ArrayList<>();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Map<String, String>> listaErrores = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String defaultMessage = error.getDefaultMessage();
            String propiedad = defaultMessage == null ? "" : environment.getProperty(defaultMessage);
            Map<String, String> campos = new HashMap<>();
            campos.put("error", error.getDefaultMessage());
            campos.put("campo", error.getField());
            campos.put("mensaje", propiedad);
            listaErrores.add(campos);
        }
        ErrorDTO errorDTO = ErrorDTO.builder().estado(HttpStatus.BAD_REQUEST).mensaje("Error Durante la Validación del Objeto " + listaErrores).build();
        return handleExceptionInternal(ex, errorDTO, headers, errorDTO.getEstado(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        errores.add(ex.getParameterName() + " No fue encontrado en el request");
        ErrorDTO errorDTO = ErrorDTO.builder().estado(HttpStatus.BAD_REQUEST).mensaje(ex.getLocalizedMessage()).build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getEstado());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        for (ConstraintViolation<?> violation : ex.getConstraintViolations())
            errores.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        ErrorDTO errorDTO = ErrorDTO.builder().estado(HttpStatus.BAD_REQUEST).mensaje(ex.getLocalizedMessage()).build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getEstado());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        Class<?> requiredType = ex.getRequiredType();
        String name = requiredType == null ? "" : requiredType.getName();
        errores.add(ex.getName() + " debería ser de tipo " + name);
        ErrorDTO errorDTO = ErrorDTO.builder().estado(HttpStatus.BAD_REQUEST).mensaje(ex.getLocalizedMessage()).build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getEstado());
    }


    @ExceptionHandler({java.lang.Exception.class})
    public ResponseEntity<Object> exception(java.lang.Exception ex) {
        ErrorDTO errorDTO = ErrorDTO.builder().estado(HttpStatus.BAD_REQUEST).mensaje(ex.getLocalizedMessage()).build();
        LogManager.getLogger(ResponseExceptionHandler.class).error(ex);
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getEstado());
    }

}
