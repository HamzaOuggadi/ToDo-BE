package net.hamzaouggadi.todobe.mappers;

import net.hamzaouggadi.todobe.dtos.AppUserDTO;
import net.hamzaouggadi.todobe.entities.AppUser;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDTO toAppUserDTO(AppUser appUser);
    AppUser toAppUser(AppUserDTO appUserDTO);
}
