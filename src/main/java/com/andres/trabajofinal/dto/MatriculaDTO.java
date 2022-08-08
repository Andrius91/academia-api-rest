package com.andres.trabajofinal.dto;

import com.andres.trabajofinal.model.Estudiante;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatriculaDTO {

    private Integer idMatricula;

    @NotNull(message = "La fecha no puede ser nula.")
    private LocalDateTime fecha;

    @NotNull(message = "El estudiante no puede ser nulo.")
    @JsonIncludeProperties(value= {"idEstudiante"})
    private EstudianteDTO estudiante;

    @NotNull(message = "Los detalles no pueden ser nulos.")
    private List<DetalleMatriculaDTO> detalle;

    @NotNull(message = "El estado no puede ser nulo.")
    private boolean estado;
}
