package com.juan.escuela.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.List;

//@Configuration
@OpenAPIDefinition(
        tags = {
                @Tag(name = "Autenticación"),
                @Tag(name = "Usuarios"),
                @Tag(name = "Profesores"),
                @Tag(name = "Alumnos"),
                @Tag(name = "Materias"),
                @Tag(name = "Materia-Alumno")

        },
        info = @Info(
                contact = @Contact(
                        name = "Juan Fredes",
                        email = "juanjgfredes@gmail.com",
                        url = "https://www.linkedin.com/in/juanjgfredes/"
                ),
                title = "Api para una escuela",
                description = "Api diseñada para una escuela secundaria, " +
                        "donde con los diferentes endpoints se podrá consultar y " +
                        "guardar datos de alumnos y profesores y su relación con las diferentes materias",
                version = "1.1.1"
        ),
        servers = {
                @Server(
                        description = "Entorno de Producción",
                        url = "https://escuela-app.onrender.com/escuela/"
                ),
                @Server(
                  description = "Entorno local",
                  url = "http://localhost:8080/escuela/"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Autenticación con JWT",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class Docs {

    /*@Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Escuela")
                        .description("Api Rest para una escuela secundaria")
                        .version("V1"));
    }*/


}

