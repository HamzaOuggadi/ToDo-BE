package net.hamzaouggadi.todobe.mappers;


import net.hamzaouggadi.todobe.dtos.PostDTO;
import net.hamzaouggadi.todobe.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "appUser.email", target = "writerEmail")
    @Mapping(source = "id", target = "id", qualifiedByName = "idToStringConverter")
    PostDTO toPostDTO(Post post);

    @Mapping(source = "writerEmail", target = "appUser.email")
    @Mapping(source = "id", target = "id", qualifiedByName = "idToLongConverter")
    Post toPost(PostDTO postDTO);


    @Named("idToStringConverter")
    default String mapIdToString(Long id) {
        return id != null ? id.toString() : null;
    }

    @Named("idToLongConverter")
    default Long mapIdToLong(String id) {
        return id != null ? Long.valueOf(id) : null;
    }
}
