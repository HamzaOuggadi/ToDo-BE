package net.hamzaouggadi.todobe.utils;


import lombok.extern.slf4j.Slf4j;
import net.hamzaouggadi.todobe.exceptions.AppUserException;
import net.hamzaouggadi.todobe.exceptions.PostException;
import net.hamzaouggadi.todobe.exceptions.TaskException;
import net.hamzaouggadi.todobe.exceptions.TodoListException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppUserException.class)
    public ResponseEntity<GenericMessage> handleAppUserException(AppUserException ex) {
        GenericMessage message = new GenericMessage(ex.getMessage());
        message.setHttpStatus(ex.getHttpStatus());
        ex.printStackTrace();
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(message);
    }

    @ExceptionHandler(value = PostException.class)
    public ResponseEntity<GenericMessage> handlePostException(PostException ex) {
        GenericMessage message = new GenericMessage(ex.getMessage());
        message.setHttpStatus(ex.getHttpStatus());
        ex.printStackTrace();
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(message);
    }

    @ExceptionHandler(value = TaskException.class)
    public ResponseEntity<GenericMessage> handleTaskException(TaskException ex) {
        GenericMessage message = new GenericMessage(ex.getMessage());
        message.setHttpStatus(ex.getHttpStatus());
        ex.printStackTrace();
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(message);
    }

    @ExceptionHandler(value = TodoListException.class)
    public ResponseEntity<GenericMessage> handleToDoListException(TodoListException ex) {
        GenericMessage message = new GenericMessage(ex.getMessage());
        message.setHttpStatus(ex.getHttpStatus());
        ex.printStackTrace();
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(message);
    }


}
