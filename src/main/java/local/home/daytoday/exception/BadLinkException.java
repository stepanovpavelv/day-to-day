package local.home.daytoday.exception;

import org.springframework.http.HttpStatus;

public final class BadLinkException extends LinkException {
    public BadLinkException(String message) {
        super(message);
    }

    @Override
    protected HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}