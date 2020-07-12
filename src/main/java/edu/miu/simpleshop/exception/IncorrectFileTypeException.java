package edu.miu.simpleshop.exception;

public class IncorrectFileTypeException extends RuntimeException {

    public IncorrectFileTypeException() {}

    public IncorrectFileTypeException(String errorMessage) {
        super(errorMessage);
    }

}
