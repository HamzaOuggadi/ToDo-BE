package net.hamzaouggadi.todobe.utils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
public class GenericMessage {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;

    public GenericMessage() {

    }

    public GenericMessage(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public GenericMessage(String message, HttpStatus httpStatus) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
