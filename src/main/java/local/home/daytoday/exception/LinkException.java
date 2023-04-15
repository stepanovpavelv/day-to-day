package local.home.daytoday.exception;

import org.springframework.http.HttpStatus;

public abstract sealed class LinkException extends RuntimeException permits BadLinkException, NotFoundLinkException {
    protected abstract HttpStatus getStatus();

    public LinkException() {
        super();
    }

    public LinkException(Throwable e) {
        super(e);
    }

    public LinkException(String message) {
        super(message);
    }

    public LinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkException(String errorCode, Object[] args, Throwable cause) {
        this(String.format(errorCode, args), cause);
    }
}