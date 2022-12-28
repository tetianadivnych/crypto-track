package com.project.javatesttask.exception;

public class CryptocurrencyNotFoundException extends RuntimeException {

    public CryptocurrencyNotFoundException(String message) {
        super(message);
    }
}
