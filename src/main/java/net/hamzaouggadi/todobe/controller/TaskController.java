package net.hamzaouggadi.todobe.controller;


import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.todobe.dtos.TaskDTO;
import net.hamzaouggadi.todobe.service.TaskService;
import net.hamzaouggadi.todobe.utils.GenericMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private MessageSource messageSource;


    @GetMapping("/by-task-id/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }


    @GetMapping("/by-user-email/{userEmail}")
    public ResponseEntity<List<TaskDTO>> getAllTasksByUserEmail(@PathVariable String userEmail) {
        return ResponseEntity.ok(taskService.getAllTasksByUserEmail(userEmail));
    }

    @GetMapping("/by-todolist-id/{todoListId}")
    public ResponseEntity<List<TaskDTO>> getAllTasksByTodoListId(@PathVariable Long todoListId) {
        return ResponseEntity.ok(taskService.getAllTasksByTodoListId(todoListId));
    }

    @PostMapping("/create")
    public ResponseEntity<GenericMessage> createTask(@RequestBody TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "task.created.success",
                        new Object[]{},
                        Locale.getDefault()
                ),
                HttpStatus.CREATED
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }


    @PutMapping("/update")
    public ResponseEntity<GenericMessage> updateTask(@RequestBody TaskDTO taskDTO) {
        taskService.updateTask(taskDTO);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "task.updated.success",
                        new Object[]{},
                        Locale.getDefault()
                ),
                HttpStatus.OK
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }


    @PatchMapping("/set-task-done/{taskId}")
    public ResponseEntity<GenericMessage> setTaskToDone(@PathVariable Long taskId) {
        taskService.setTaskToDoneById(taskId);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "task.set.done.success",
                        new Object[]{},
                        Locale.getDefault()
                ),
                HttpStatus.OK
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }


    @DeleteMapping("/delete-by-id/{taskId}")
    public ResponseEntity<GenericMessage> deleteTaskById(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "task.deleted.success",
                        new Object[]{},
                        Locale.getDefault()
                ),
                HttpStatus.OK
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }

}
