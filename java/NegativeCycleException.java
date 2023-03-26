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