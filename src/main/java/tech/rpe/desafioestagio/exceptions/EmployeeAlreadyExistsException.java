package tech.rpe.desafioestagio.exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException {

    public EmployeeAlreadyExistsException(final String message) {
        super(message);
    }
}
