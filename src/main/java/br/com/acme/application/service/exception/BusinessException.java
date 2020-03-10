package br.com.acme.application.service.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6738104906266324294L;

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BusinessException(String message) {
        super(message);
    }

}
