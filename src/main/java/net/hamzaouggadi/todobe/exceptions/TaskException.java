package net.hamzaouggadi.todobe.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
public class TaskException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;


    public TaskException() {

    }

    public TaskException(String message) {
        super(message);
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public TaskException(String message, HttpStatus httpStatus) {
        super(message);
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
