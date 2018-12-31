package tech.overpass.conferauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
public class LargePostException extends RuntimeException {

    public LargePostException() {
    }

    public LargePostException(String message) {
        super(message);
    }
}
