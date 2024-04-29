package tech.rpe.desafioestagio.exceptions.handler;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    private List<String> errors;

    public ApiErrors(String error) {
        this.errors = Arrays.asList(error);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }



    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}