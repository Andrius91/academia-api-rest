package com.andres.trabajofinal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {

    @NotNull(message = "El id del curso no puede ser nulo.")
    private Integer idCurso;

    @NotNull(message = "El nombre no puede ser nulo.")
    @NotEmpty(message = "El nombreno puede estar vacio.")
    @Size(min = 3, max = 50, message = "El nombre del curso debe tener 3 carácteres cómo mínimo y 50 como máximo.")
    private String nombre;

    @NotNull(message = "Las siglas no pueden ser nulos.")
    @NotEmpty(message = "Las siglas no pueden estar vaccias.")
    @Size(min = 3, max = 10, message = "El nombre de las siglas debe tener 3 carácteres cómo mínimo y 10 como máximo.")
    private String siglas;

    @NotNull(message = "El estado no puede ser nulo.")
    private boolean estado;
}
