package net.hamzaouggadi.todobe.mappers;


import net.hamzaouggadi.todobe.dtos.PostDTO;
import net.hamzaouggadi.todobe.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "appUser.email", target = "writerEmail")
    PostDTO toPostDTO(Post post);
    @Mapping(source = "writerEmail", target = "appUser.email")
    Post toPost(PostDTO postDTO);
}
