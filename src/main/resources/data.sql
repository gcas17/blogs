
INSERT INTO author (name,email,birthDate, phone) VALUES ( 'George Castillo Farfan', 'georgekcastillo@bcp.com.pe', '2020-01-30T11:00', '941131257');
INSERT INTO blog (name, description, url, status, author_id) VALUES ('Frutas', 'Frutas description', 'frutas.blogspot.com.pe', 'Activo', 1);
INSERT INTO post (title, date, status, content, blog_id) VALUES ('¿Que frutas son mas dulces?', '2021-01-30T11:00', 'Publicado', 'CONTENIDO DEL POST DE FRUTAS DULCES', 1);
INSERT INTO comment (date, name, status, post_id) VALUES ('2021-02-02T11:00', 'El platano es muy dulce.', 'Activo', 1);

INSERT INTO author (name,email,birthDate, phone) VALUES ( 'Martin Scorsese', 'martinscorsese@bcp.com.pe', '2018-01-30T11:00', '941131258');
INSERT INTO blog (name, description, url, status, author_id) VALUES ('Cine de superheroes', 'Cine de superheroes description', 'cinesuperheroes.blogspot.com.pe', 'Activo', 2);
INSERT INTO post (title, date, status, content, blog_id) VALUES ('Mejores peliculas de superheroes', '2021-01-30T11:00', 'Publicado', 'CONTENIDO DEL POST DE MEJORES PELICULAS DE SUPERHEROES', 2);
INSERT INTO comment (date, name, status, post_id) VALUES ('2021-02-02T11:00', 'Me gusto la segunda de batman de Christopher Nolan.', 'Activo', 2);