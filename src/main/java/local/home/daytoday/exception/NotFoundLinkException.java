package local.home.daytoday.exception;

import org.springframework.http.HttpStatus;

public final class NotFoundLinkException extends LinkException {
    public NotFoundLinkException(String message) {
        super(message);
    }

    @Override
    protected HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}