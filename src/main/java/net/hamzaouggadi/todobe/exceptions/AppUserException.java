package net.hamzaouggadi.todobe.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
public class AppUserException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;

    public AppUserException() {

    }

    public AppUserException(String message) {
        super(message);
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public AppUserException(String message, HttpStatus httpStatus) {
        super(message);
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
