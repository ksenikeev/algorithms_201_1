package ru.itis.algorithms_201_1.paramonov;

public class NegativeCycleException extends Exception {
    public NegativeCycleException() {
        super();
    }

    public NegativeCycleException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}