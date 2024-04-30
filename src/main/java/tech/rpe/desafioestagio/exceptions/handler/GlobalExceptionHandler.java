package tech.rpe.desafioestagio.exceptions.handler;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.rpe.desafioestagio.exceptions.CustomerAlreadyExistsException;
import tech.rpe.desafioestagio.exceptions.CustomerNotFoundException;
import tech.rpe.desafioestagio.exceptions.EmployeeAlreadyExistsException;
import tech.rpe.desafioestagio.exceptions.EmployeeNotFoundException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleBadRequestException(final BadRequestException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handleCustomerNotFoundException(CustomerNotFoundException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiErrors handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handleEmployeeNotFoundException(EmployeeNotFoundException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiErrors handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiErrors handleIllegalArgumentException(IllegalArgumentException ex){
        return new ApiErrors(ex.getMessage());
    }

}
