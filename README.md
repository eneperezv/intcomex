# intcomexAPI

La API de Productos y Categorías es una aplicación RESTful desarrollada en Spring Boot que permite gestionar productos y sus categorías asociadas. Proporciona endpoints para consultar y crear productos y categorías, y está diseñada para ser escalable y fácil de mantener.

Descripción
-------------
La arquitectura de la API de Productos sigue el patrón de diseño de capas común en aplicaciones Spring Boot, que incluye las siguientes capas principales:
1. Capa de Controladores (Controller Layer): Gestiona las solicitudes HTTP entrantes y las dirige a los repositorios adecuados.
2. Capa de Repositorios (Repository Layer): Interactúa con la base de datos para realizar operaciones CRUD.
3. Capa de Modelo (Model Layer): Define las entidades y sus relaciones.

[![Arquitectura](https://soluvial.co/mithrandir/apiRest/Imagen1.png "Arquitectura")](https://soluvial.co/mithrandir/apiRest/Imagen1.png "Arquitectura")

Instrucciones
-------------
- Clonar repositorio a local.
- Importar a IDE de preferencia (Usamos Eclipse 2024-03 4.31.0)
- En el directorio docs se encuentra un archivo SQL para incializar la base de datos.
- Se debe modificar el archivo application.properties para la conexion a su base de datos local.
<pre>
spring.datasource.url= jdbc:mysql://localhost:3306/intcomex?useSSL=false
spring.datasource.username= root
spring.datasource.password= 
</pre>
- Ejecutar la aplicacion como Spring Boot Application
	- Clic derecho sobre el proyecto
	- Run as > Spring Boot App

Endpoints
-------------
Crear Categoría
URL:    http://localhost:8080/api/category
Metodo: POST
Recibe: JSON
<pre>
{
    "categoryName": "CLOUD",
    "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
    "picture": "-"
}
</pre>

Consultar Todas las Categorías
URL:    http://localhost:8080/api/categories
Metodo: GET

Consultar Categoría por ID
URL:    http://localhost:8080/api/categories/{idCategoria}
Metodo: GET

Crear Productos
URL:    http://localhost:8080/api/product
Metodo: POST
ATENCION: Este metodo crea 100.000 registros en la base de datos según la solicitud. El proceso demora aproximadamente 2 minutos y medio.

Consultar Todos los Productos
URL:    http://localhost:8080/api/products?pageNumber={numeroPagina}&pageSize={tamañoPagina}
Metodo: GET

Consultar Producto por ID
URL:    http://localhost:8080/api/products/{idProducto}
Metodo: GET


Swagger
-------------
Se incluye herramienta Swagger para la documentacion de la API. Esta se despliega cuando se levanta la aplicacion.
URL:    http://localhost:8080/swagger-ui/index.html

Tecnologías Utilizadas
-------------
- Spring Boot 
- Spring Data JPA 
- MySQL Database 
- JUnit 5 
- Mockito

La arquitectura de la API de Productos está diseñada para ser escalable y fácil de mantener, utilizando las mejores prácticas de Spring Boot. En el futuro, se planea implementar caching para mejorar el rendimiento y añadir más endpoints para funcionalidades avanzadas.
