package net.hamzaouggadi.todobe.dtos;

import net.hamzaouggadi.todobe.enums.AppUserType;

import java.time.LocalDateTime;

public record AppUserDTO(String email,
                         String password,
                         LocalDateTime registrationDate,
                         AppUserType appUserType) {

}
