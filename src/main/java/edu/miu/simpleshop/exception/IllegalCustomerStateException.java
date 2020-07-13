package edu.miu.simpleshop.exception;

public class IllegalCustomerStateException extends RuntimeException {

    public IllegalCustomerStateException() {}
    public IllegalCustomerStateException(String errorMessage) {
        super(errorMessage);
    }
}
