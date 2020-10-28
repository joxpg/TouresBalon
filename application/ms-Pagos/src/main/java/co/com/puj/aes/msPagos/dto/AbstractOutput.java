package co.com.puj.aes.msPagos.dto;

public abstract class AbstractOutput {

    private int status;
    private String message;

    public AbstractOutput() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
