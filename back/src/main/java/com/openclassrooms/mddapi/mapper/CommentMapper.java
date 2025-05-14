package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.Entity.Comment;
import com.openclassrooms.mddapi.Entity.User;
import org.mapstruct.*;

/**
 * Mapper pour convertir entre l'entité Comment et le DTO CommentDto.
 * Utilise MapStruct pour le mappage automatique des champs entre les entités et les DTOs.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * Convertit une entité Comment en un DTO CommentDto.
     *
     * @param comment L'entité Comment à convertir.
     * @return CommentDto Le DTO Comment converti.
     */
    @Mapping(target = "username", source = "author.username")
    CommentDto toDto(Comment comment);

    /**
     * Convertit un DTO CommentDto en une entité Comment.
     *
     * @param dto Le DTO CommentDto à convertir.
     * @param author L'utilisateur auteur du commentaire.
     * @return Comment L'entité Comment convertie.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "article", ignore = true)
    @Mapping(target = "author", source = "author")
    Comment toEntity(CommentDto dto, User author);
}
