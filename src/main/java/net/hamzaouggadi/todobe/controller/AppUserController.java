package net.hamzaouggadi.todobe.controller;


import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.todobe.dtos.AppUserDTO;
import net.hamzaouggadi.todobe.service.AppUserService;
import net.hamzaouggadi.todobe.utils.GenericMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;
    private final MessageSource messageSource;

    @GetMapping("/by-email/{email}")
    public ResponseEntity<AppUserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(appUserService.getUserByEmail(email));
    }


    @PostMapping("/create-user")
    public ResponseEntity<GenericMessage> addUser(@RequestBody AppUserDTO userDTO) {
        appUserService.saveUser(userDTO);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "user.created.success",
                        new Object[]{userDTO.email()},
                        Locale.getDefault()
                ),
                HttpStatus.CREATED
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }


    @PutMapping("/update-user")
    public ResponseEntity<GenericMessage> updateUser(@RequestBody AppUserDTO userDTO) {
        appUserService.updateUser(userDTO);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "user.updated.success",
                        new Object[]{userDTO.email()},
                        Locale.getDefault()
                ),
                HttpStatus.OK
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }


    @DeleteMapping("/delete-user/{email}")
    public ResponseEntity<GenericMessage> deleteUser(@PathVariable String email) {
        appUserService.deleteUser(email);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "user.deleted.success",
                        new Object[]{email},
                        Locale.getDefault()
                ),
                HttpStatus.OK
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }
}
