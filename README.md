##Spring Security 6
###Implementing ROLES and JWT

####Important things to consider:
* El prefijo ROLE_ es una convención estándar en Spring Security para roles de usuario, que facilita la integración con
mecanismos de autorización. Cambiar a hasAuthority en la configuración de seguridad permite una comparación directa con
los valores de roles especificados, mientras que Spring automáticamente añade el prefijo ROLE_ en hasRole. En
JwtService, añadir ROLE_ en las reclamaciones del token asegura que los roles se validen correctamente. Adaptar
User.getAuthorities() para incluir ROLE_ asegura que los roles se reconozcan como se espera en el sistema de
autenticación, mejorando la consistencia y compatibilidad con las prácticas de Spring Security.