package edu.miu.simpleshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The item you're looking for does not exist")
    public ModelAndView handleEntityNotFound(EntityNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getMessage());
        modelAndView.setViewName("errors/itemNotFound");
        return modelAndView;
    }


    @ExceptionHandler(UndeletableProductException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView handleUndeletableProduct(UndeletableProductException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex.getMessage());
        modelAndView.setViewName("errors/undeletableProduct");
        return modelAndView;
    }
}
