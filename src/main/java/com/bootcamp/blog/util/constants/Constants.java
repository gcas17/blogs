package com.bootcamp.blog.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String BLOG_STATUS_INVALID_ERROR_MESSAGE = "El estado del blog no es válido. Debe ser 'Activo' o 'Inactivo'.";

    public static final String POST_STATUS_INVALID_ERROR_MESSAGE = "El estado del post no es válido. Debe ser 'Borrador' o 'Publicado'.";

    public static final String COMMENT_STATUS_INVALID_ERROR_MESSAGE = "El estado del comentario no es válido. Debe ser 'Activo' o 'Inactivo'.";

    public static final String AUTHOR_NOT_FOUND_ERROR_MESSAGE = "No se encontró el autor especificado.";

    public static final String BLOG_NOT_FOUND_ERROR_MESSAGE = "No se encontró el blog especificado.";

    public static final String POST_NOT_FOUND_ERROR_MESSAGE = "No se encontró el post especificado.";

    public static final String COMMENT_NOT_FOUND_ERROR_MESSAGE = "No se encontró el comentario especificado.";

    public static final String AUTHOR_INVALID_BIRTHDAY_ERROR_MESSAGE = "La edad del autor debe ser mayor o igual a 18 años.";

    public static final String BLOG_LIMIT_BY_AUTHOR_ERROR_MESSAGE = "Los autores solo pueden tener máximo 03 blogs.";

    public static final String POST_SAME_DAY_ERROR_MESSAGE = "Solo se puede realizar una publicación por día por cada blog.";

    public static final String POST_WITH_BLOG_INACTIVE_ERROR_MESSAGE = "Solo se puede registrar publicaciones en blogs con estado activo.";

    public static final String COMMENT__WITH_POST_INACTIVE_ERROR_MESSAGE = "Solo se puede registrar comentarios en publicaciones con estado 'Publicado'.";

    public static int LEGAL_AGE = 18;
}
