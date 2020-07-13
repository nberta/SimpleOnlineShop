package edu.miu.simpleshop.exception;

public class UndeletableProductException extends RuntimeException{

    public UndeletableProductException() {}
    public UndeletableProductException(String errorMessage) {
        super(errorMessage);
    }
}
