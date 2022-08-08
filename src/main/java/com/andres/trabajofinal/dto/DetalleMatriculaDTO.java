package com.andres.trabajofinal.dto;

import com.andres.trabajofinal.model.Curso;
import com.andres.trabajofinal.model.Matricula;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
public class DetalleMatriculaDTO {

    @JsonIgnore
    private MatriculaDTO matricula;

    @JsonIgnore
    private Integer idDetalle;

    @NotNull(message = "El curso no puede ser nulo.")
    @JsonIncludeProperties(value= {"idCurso"})
    private CursoDTO curso;

    @NotNull(message = "El aula no puede ser nula.")
    @NotEmpty(message = "El aula no puede estar vacia.")
    @Size(min = 2, max = 10, message = "El aula debe tener 2 carácteres como mínimo y 10 como máximo.")
    private String aula;
}
