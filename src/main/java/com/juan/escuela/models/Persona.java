package com.juan.escuela.models;

import com.juan.escuela.podam.DniStrategyPodam;
import com.juan.escuela.podam.EmailPodamStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStrategyValue;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "el nombre no puede ser nulo")
    @NotBlank(message = "el nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "el apellido no puede ser nul")
    @NotBlank(message = "el apellido no puede estar vacio")
    private String apellido;
    @Pattern(regexp = "[0-9]{8}", message = "el dni debe contener 8 numeros sin puntos")
    @PodamStrategyValue(DniStrategyPodam.class)
    private String dni;
    private String telefono;
    private String celular;
    @Email(message = "formato de e-mail no valido")
    @PodamStrategyValue(EmailPodamStrategy.class)
    private String email;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private char sexo;
    private String direccion;
    @Column(name = "create_at")
    private LocalDateTime create;

    public Persona(int id) {
        this.id = id;
    }

}
