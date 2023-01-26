# JFSD_back-end
Repositorio para fuentes del back-end relacionadas a la evaluación final del curso de MitoCode - Java Full Stack Developer (Edición 2022-V)

## Tener en cuenta:
Ejecutar estos scripts en la base de datos para habilitar el módulo de "Signos vitales" a los usuarios administradores.

````
INSERT INTO menu(id_menu, name, icon, url) VALUES (11, 'Vital signs', 'monitor_heart', '/pages/vital-sign');

INSERT INTO menu_role (id_menu, id_role) VALUES (11, 1);
````
