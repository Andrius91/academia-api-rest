package com.andres.trabajofinal.service;

import com.andres.trabajofinal.model.Estudiante;

import java.util.List;
import java.util.Map;

public interface IEstudiantesService extends ICRUD<Estudiante, Integer> {

    List<Estudiante> listaDesc();
}
