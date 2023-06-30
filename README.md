# Escuela-backend

Este proyecto fue creado con el objetivo de demostrar mis habilidades y conocimientos en el desarrollo de backend. La aplicación consiste en una API Rest conectada a una base de datos MySQL, la cual se encarga de persistir la información.

La API permite crear, modificar, listar y eliminar datos de estudiantes, profesores y materias, y cómo se relacionan entre sí. Además, cuenta con características de validación de datos y manejo de excepciones para asegurar la integridad de los mismos.

Desarrolle este proyecto en Java 11 utilizando el framework Spring Boot. También he usado el lenguaje SQL para diseñar y configurar la base de datos MySQL.

## Notas de la Versión

### v1.1.1

En esta nueva version se ha agregado una nueva funcionalidad a la API y se ha cambiado la version de los endpoints.

Principales cambios: 

- Se agregó seguridad mediante JWT y roles
- Se cambió el versionado de los endpoints a v2, ya que ahora se requiere autenticación para acceder a ellos
- Se adecuó la [documentación](documentacion) en Swagger para la nueva implementación de [seguridad](#seguridad) 

## <a name="despliegue"></a>Despliegue

Deployee mi Api Rest de forma gratuita en [Render](https://render.com/), donde podra probar la Api Rest sin necesidad de instalarla. 

El link para acceder a la [documentación](#documentacion) de la misma es https://escuela-app.onrender.com/escuela/swagger-ui.html. En ella, mediante la documentation en Swagger, podra informarse como funciona e interactuar con la Api desde alli. Agregue datos ficticios a la base de datos para que sea más interactiva.

**NOTA:** La Api, al ser desplegada en un servicio gratuito, puede fallar o ir lenta. En ese caso puede [instalarla en su maquina de forma tradicional](#tradicional) o [arrancarla desde un contenedor](#docker) mediante [Docker](https://www.docker.com/) siguiendo las instrucciones a continuación.

## <a name="seguridad"></a>Seguridad

La seguridad implementada es por JWT con roles:

- ### Roles
Existen 3 tipos de roles de usuarios: ADMIN, USUARIO y INVITADO.
1) ADMIN: Tiene permitido crear, ver y eliminar usuarios, también puede acceder y modificar a la información de profesores, alumnos y sus materias
2) USUARIOS: Tiene permitido ver y modificar la información de profesores, alumnos y materias.
3) INVITADO: Tiene permitido solo ver la información de profesores, alumnos y materias, sin poder realizar modificaciones.

- ### Login
Para logearse solo será necesario `username` y `password`. Si las credenciales son correctas, se le proporcionara un token para poder acceder a los diferentes endpoints dependiendo de su rol.

- ### Registro
Para registrarse, como se explico anteriormente, debera tener el rol de ADMIN. Solo será necesario él `username`, `password` y `roles`.

- ### Eliminar usuario
Se podrá eliminar usuarios con el `id` o el `username` del mismo.

Cree un usuario con rol de ADMIN para que se pueda probar la API:
- `username` = director
- `password` = 12345678

Para más información acceda a la [documentación](#documentacion).

## <a name="instalacion"></a>Instalación

### Requerimientos generales:
- [Java 11](https://www.java.com/en/) 
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://www.mysql.com/downloads/)

#### <a name="tradicional"></a>Instalación de forma tradicional:
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

Para utilizarla siga la [documentacion](#documentacion) proporcionada.

#### <a name="docker"></a>Instalación mediante Docker:

Instale [docker](https://www.docker.com/) y [docker compose](https://docs.docker.com/compose/) en su computadora y corra el siguiente comando en una terminal abierta dentro de la ubicación del proyecto: 
```
docker compose up -d
```
Espere un tiempo a que la aplicación arranque y estará lista para usar.
Para utilizarla siga la [documentacion](#documentacion) proporcionada.

Cuando quiera dejar de probarla solo,y no ocupe recursos, ponga el comando:
```
docker compose down
```
Para usarla siga la [documentacion](#documentacion) proporcionada.

## Herramientas, Tecnologías y tecnicas utilizadas

### Tecnologias principales: 
- [Java 11](https://www.java.com/en/) 
- [Spring Boot 2.7.5](https://spring.io/)
- [MySQL 8](https://www.mysql.com/)
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
En los controllers se utiliza los endpoints y los metodos http para que el usuario consulte, envie, modifique y elimine datos. Cuando estos son usados los metodos retornan un `ResponseEntity` que es una clase proporcionada por el Spring para las respuestas http. Para hacer estas tareas utilizo los services que son inyectados en la clase. 

- ### Security
En security implementé la seguridad mediante JWT y roles. Para manejar la autenticación usé un `AuthenticationProvider` administrado por el `AuthenticationManager`. Además, agregue un filtro, que se ejecuta en cada consulta, del token donde se verifica que sea correcto, si es correcto le pasa la información del usuario al contexto de seguridad de Spring. En caso de que no pase los filtros, implemente un `AuthenticationEntryPoint` que revisa cuál es el error y devuelvo un mensaje acorde. 

- #### Test
En los test controlo todas la capas de mi aplicación. Creando objetos especificos para poder testear cada metodos de las diferentes clases, en mi caso para poder crear elementos de forma aleatoria y sencilla utilize [Podam](https://mtedone.github.io/podam/). También utilice [Mockito](https://site.mockito.org/) que permite simular obtejos y comportamientos. Una vez tenía los objetos, los testaba con [JUnit](https://junit.org/junit5/) y las herramientas para testear que proporciona [Spring](https://spring.io/). También utilizo [H2](https://www.h2database.com/html/main.html), que es una base de datos en memoria, para probar los repositories. De esta manera, pude asegurarme de que todos los métodos estuvieran funcionando correctamente y de que los datos se estuvieran procesando de manera adecuada. NOTA: Aunque en este caso hice todos los test al final de todo, en realidad es una buena práctica hacerlo apenas terminas de desarrollar cada clase.

## <a name="documentacion"></a>Documentación

- La documentación de la API, ya [desplegada](#despliegue), está disponible en el siguierte link: https://escuela-app.onrender.com/escuela/swagger-ui.html

- La documentación de la API, [instalada](#instalacion) y en tiempo de ejecucion, está disponible en el link: http://localhost:8080/escuela/swagger-ui.html

La documentación al estar hecha con [Swagger](https://swagger.io/) permite no solo informarse de que forma funciona la Api, sino que tambien es posible interactuar con ella.

Para probar la API puede accerder con las credenciales dadas en el apartado de [seguridad](#seguridad), para hacerlo siga las siguientes instrucciones: 

