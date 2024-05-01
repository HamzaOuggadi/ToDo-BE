package net.hamzaouggadi.todobe.mappers;

import net.hamzaouggadi.todobe.dtos.AppUserDTO;
import net.hamzaouggadi.todobe.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface AppUserMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "idToStringConverter")
    AppUserDTO toAppUserDTO(AppUser appUser);

    @Mapping(source = "id", target = "id", qualifiedByName = "idToLongConverter")
    AppUser toAppUser(AppUserDTO appUserDTO);


    @Named("idToStringConverter")
    default String mapIdToString(Long id) {
        return id != null ? id.toString() : null;
    }

    @Named("idToLongConverter")
    default Long mapIdToLong(String id) {
        return id != null ? Long.valueOf(id) : null;
    }
}
