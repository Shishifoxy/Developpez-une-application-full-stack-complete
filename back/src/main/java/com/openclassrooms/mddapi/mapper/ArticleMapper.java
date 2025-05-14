package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.Entity.Article;
import com.openclassrooms.mddapi.dto.ArticleDto;
import org.mapstruct.*;

/**
 * Mapper pour convertir entre l'entité Article et le DTO ArticleDto.
 * Utilise MapStruct pour le mappage automatique des champs entre les entités et les DTOs.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ArticleMapper {

    /**
     * Convertit une entité Article en un DTO ArticleDto.
     *
     * @param article L'entité Article à convertir.
     * @return ArticleDto Le DTO Article converti.
     */
    @Mapping(target = "author", source = "article.author.username")
    @Mapping(target = "date", expression = "java(article.getCreatedAt().toString())")
    @Mapping(target = "theme", source = "article.theme.id")
    ArticleDto toDto(Article article);

    /**
     * Convertit un DTO ArticleDto en une entité Article.
     *
     * @param dto Le DTO ArticleDto à convertir.
     * @return Article L'entité Article convertie.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "theme", ignore = true)
    Article toEntity(ArticleDto dto);
}
