package com.andres.trabajofinal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstudianteDTO {

    @NotNull(message = "El id del estudiante no puede ser nulo.")
    private Integer idEstudiante;

    @NotNull(message = "El nombre no puede ser nulo.")
    @NotEmpty(message = "El nombre no puede estar vacio.")
    @Size(min = 3, max = 100, message = "El nombre debe tener 3 carácteres cómo mínimo y 100 como máximo.")
    private String nombres;

    @NotNull(message = "El apellido no puede ser nulo.")
    @NotEmpty(message = "El apellido no puede estar vacio.")
    @Size(min = 3, max = 100, message = "El apellido debe tener 3 carácteres cómo mínimo y 100 como máximo.")
    private String apellidos;

    @NotNull(message = "El dni no puede ser nulo.")
    @NotEmpty(message = "El dni no puede estar vacio.")
    @Size(min = 3, max = 10, message = "El dni debe tener 3 carácteres cómo mínimo y 10 como máximo.")
    private String dni;

    @Digits(integer=2, fraction=1)
    private double edad;
}
