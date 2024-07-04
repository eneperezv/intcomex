# intcomexAPI

La API de Productos y Categorías es una aplicación RESTful desarrollada en Spring Boot que permite gestionar productos y sus categorías asociadas. Proporciona endpoints para consultar y crear productos y categorías, y está diseñada para ser escalable y fácil de mantener.

Descripción
-------------
La arquitectura de la API de Productos sigue el patrón de diseño de capas común en aplicaciones Spring Boot, que incluye las siguientes capas principales:
1. Capa de Controladores (Controller Layer): Gestiona las solicitudes HTTP entrantes y las dirige a los repositorios adecuados.
2. Capa de Repositorios (Repository Layer): Interactúa con la base de datos para realizar operaciones CRUD.
3. Capa de Modelo (Model Layer): Define las entidades y sus relaciones.

[![Arquitectura](https://soluvial.co/mithrandir/apiRest/Imagen1.png "Arquitectura")](https://soluvial.co/mithrandir/apiRest/Imagen1.png "Arquitectura")

Tecnologías Utilizadas
-------------
- Spring Boot 
- Spring Data JPA 
- MySQL Database 
- JUnit 5 
- Mockito

La arquitectura de la API de Productos está diseñada para ser escalable y fácil de mantener, utilizando las mejores prácticas de Spring Boot. En el futuro, se planea implementar caching para mejorar el rendimiento y añadir más endpoints para funcionalidades avanzadas.
