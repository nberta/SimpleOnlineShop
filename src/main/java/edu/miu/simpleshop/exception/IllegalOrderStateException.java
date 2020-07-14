package edu.miu.simpleshop.exception;

public class IllegalOrderStateException extends RuntimeException {

    public IllegalOrderStateException() {}

    public IllegalOrderStateException(String errorMessage) {
        super(errorMessage);
    }
}
