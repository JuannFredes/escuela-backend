# Escuela-backend

Este proyecto fue creado con el objetivo de demostrar mis habilidades y conocimientos en el desarrollo de backend. La aplicación consiste en una API Rest conectada a una base de datos MySQL, la cual se encarga de persistir la información.

La API permite crear, modificar, listar y eliminar datos de estudiantes, profesores y materias, y cómo se relacionan entre sí. Además, cuenta con características de validación de datos y manejo de excepciones para asegurar la integridad de los mismos.

Desarrolle este proyecto en Java 11 utilizando el framework Spring Boot. También he usado el lenguaje SQL para diseñar y configurar la base de datos MySQL.

## Instalación

### Requerimientos generakes:
- [Java 11](https://www.java.com/en/) 
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://www.mysql.com/downloads/)

#### Corriendo la aplicación desde la terminal:
1. Clona el repositorio
   ```
   git clone https://github.com/JuannFredes/escuela-backend.git
   ```
2. Abre el archivo `application-dev.properties` en la ruta `escuela-backend/src/main/resources` y modifica las siguientes líneas con la información correspondiente a tu base de datos MySQL:
   ```
   spring.datasource.username=[tu-nombre-usuario]
   spring.datasource.password=[tu-contraseña]
   ```
3. Abre el archivo `liquibase-dev.properties` en la ruta `escuela-backend/src/main/resources` y modifica las siguientes líneas con la información correspondiente a tu base de datos MySQL:
   ```
   username=[tu-nombre-usuario]
   password=[tu-contraseña]
   ```
4. En tu MySQL crea una base de datos llamada `escuela`
   ```
   CREATE DATABASE escuela;
   ```
5. Estando en la carpeta `escuela-backend` ejecuta el siguiente comando en la terminal para compilar el proyecto
   ```
   mvn clean package
   ```
6. En la misma terminal, para poder crear todas las tablas necesarias de la base de datos, ejecuta 
   ```
   mvn liquibase:update -Pdev
   ```
7. Finalmente, para iniciar el proyecto ejecuta
   ```
   java -jar target/escuela-0.0.1-SNAPSHOT.jar
   ```
#### Corriendo la aplicación con Docker:

Instale [docker](https://www.docker.com/) y [docker compose](https://docs.docker.com/compose/) en su computadora y corra el siguiente comando: 
```
docker compose up -d
```
Espere un tiempo a que la aplicación arranque y estará lista para usar. Cuando quiera dejar de probarla solo,y no ocupe recursos, ponga el comando:
```
docker compose down
```

## Herramientas, Tecnologías y tecnicas utilizadas

### Tecnologias principales: 
- [Java](https://www.java.com/en/) 
- [Spring](https://spring.io/)
- [MySQL](https://www.mysql.com/)
- [Maven](https://maven.apache.org/)
### Herramientas secundarias:
- [Hibernate](https://hibernate.org/)
- [MapStruct](https://mapstruct.org/)
- [Lombok](https://projectlombok.org/)
- [Liquibase](https://www.liquibase.org/)
- [JUnit](https://junit.org/)
- [Mockito](https://site.mockito.org/)
- [Podam](https://mtedone.github.io/podam/)
- [H2](https://www.h2database.com/html/main.html)

A continuation, detallaré de forma concisa como cree la Api Rest y como fui utilizando las herramientas:
- #### Base de datos
Diseñe la base de datos utilizando MySQL. Para contectarme a ella, hice uso de [Spring Data](https://spring.io/projects/spring-data) y el driver correspondiente, configurando la conexión en el archivo `application.properties`. Además, emplee [Liquibase](https://www.liquibase.org/) para la migración de la base de datos. [Liquibase](https://www.liquibase.org/) es una herramienta que permite gestionar cambios en la estructura de la base de datos de forma controlada y automatizada, que permite crear todas las tablas utilizando el plugin de [Liquibase](https://www.liquibase.org/) mediante [Maven](https://maven.apache.org/).

- #### Models
Para poder crear los modelos de la base de datos utilicé JPA, que es una especificación de [Java](https://www.java.com/en/) que define una API para el mapeo objeto-relacional. Para utilizar JPA, se necesita de un ORM (Mapeo Objeto-Relacional) que la implemente, en este caso se utiliza [Hibernate](https://hibernate.org/) que es el ORM que utiliza [Spring](https://spring.io/). Con ella mapie todas las tablas de la base de datos como sus relaciones, en ese caso relaciones bidireccionales uno a muchos y muchos a muchos. Para simplificar la creación de código, use [Lombok](https://projectlombok.org/), que mediante anotaciones permite generar código, como por ejemplo los getters y setters, y utilizar el patrón de diseño Builder. 

- #### DTO y Mappers
Los DTO (Data Transfer Object) se utilizan para representar los datos que seran enviados entre las diferentes capaz de la API. Los utilicé para poder enviar los datos que son requeridos por los endpoints, y en algún caso para recibir informacion. Para poder mapearlos utilice la herramienta [MapStruct](https://mapstruct.org/) que es un generador de código que simplifica los mapeos entre los objetos en [Java](https://www.java.com/en/).

- #### Repositories
Para el repositorio utilize interfaces que heredan de las interfaces `CrudRepository` que es una interfaz proporcionada por [Spring Data](https://spring.io/projects/spring-data) que define métodos genéricos para realizar operaciones CRUD (Crear, Leer, Actualizar y Borrar) y mediante el uso de genericos puede ejecutar esas operaciones con los modelos que proporcione.

- #### Services
En los services va la logica del negocio, y es donde cree los metodos que procesan o retornan consultas. Utilizando la inyeccion de dependencia, en este caso ayudandome con [Lombok](https://projectlombok.org/), con los repositories y los mapper para realizar la logica para comunicarme con la base de datos y retornan los DTO.

- #### Exceptions
En los services pueden surgir situaciones imprevistas que pueden generar errores. Para manejar estas situaciones, se pueden utilizar excepciones personalizadas. En mi caso, cree la clase `AppException` que hereda de `RuntimeException`, para poder lanzarla en caso de que ocurra algún error específico. Esta excepción está diseñada para ser manejada por el `RestExceptionHandler`, el cual es responsable de controlar y capturar esta y otras excepciones que le indique, en tiempo de ejecución. Al capturarlas, el `RestExceptionHandler` devuelve una respuesta al usuario en forma de DTO que muestra el error de manera clara y legible

- #### Controllers
En los controllers se utiliza los endpoints y los metodos http para que el usuario consulte, envie, modifique y elimine datos. Cuando estos son usados los metodos retornan un `ResponseEntity` que es una clase proporcionada por el Spring para las respuestas http. Para hacer hacer estas tareas utilizo los services que son inyectados en la clase. 

- #### Test
En los test controlo todas la capas de mi aplicación. Creando objetos especificos para poder testear cada metodos de las diferentes clases, en mi caso para poder crear elementos de forma aleatoria y sencilla utilize [Podam](https://mtedone.github.io/podam/). Tambien utilice [Mockito](https://site.mockito.org/) que permite simular obtejos y comportamientos. Una vez tenía los objetos, los testaba con [JUnit](https://junit.org/junit5/) y las herramientas para testear que proporciona [Spring](https://spring.io/). También utilizo [H2](https://www.h2database.com/html/main.html), que es una base de datos en memoria, para probar los repositories. De esta manera, pude asegurarme de que todos los métodos estuvieran funcionando correctamente y de que los datos se estuvieran procesando de manera adecuada. NOTA: Aunque en este caso hice todos los test al final de todo, en realidad es una buena práctica hacerlo apenas terminas de desarrollar cada clase.  

## Documentación de API

La documentación de la API está disponible en tiempo de ejecución de la aplicación en la siguiente URL: http://localhost:8080/escuela/swagger-ui.html

Para ejecutar la aplicación, siga las instrucciones en la sección de instalación y configuración de este documento. Una vez ejecutándose la aplicación vaya al link proporcionado para ver la documentación del funcionamiento de la API.
