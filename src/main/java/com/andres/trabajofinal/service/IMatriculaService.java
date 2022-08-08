package com.andres.trabajofinal.service;

import com.andres.trabajofinal.model.DetalleMatricula;
import com.andres.trabajofinal.model.Estudiante;
import com.andres.trabajofinal.model.Matricula;

import java.util.List;
import java.util.Map;

public interface IMatriculaService extends ICRUD<Matricula, Integer> {
    Matricula saveTransactional(Matricula sale, List<DetalleMatricula> details);

    Map<String, List<Estudiante>> mostrarRelacion();
}