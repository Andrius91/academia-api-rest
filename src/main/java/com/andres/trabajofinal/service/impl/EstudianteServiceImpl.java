package com.andres.trabajofinal.service.impl;

import com.andres.trabajofinal.model.DetalleMatricula;
import com.andres.trabajofinal.model.Estudiante;
import com.andres.trabajofinal.repo.IEstudianteRepo;
import com.andres.trabajofinal.repo.IGenericRepo;
import com.andres.trabajofinal.service.IEstudiantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, Integer> implements IEstudiantesService {

    @Autowired
    private IEstudianteRepo repo;

    @Override
    protected IGenericRepo<Estudiante, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Estudiante> listaDesc() {
        return repo.findAll().stream()
                .sorted(Comparator.comparing(Estudiante::getEdad, Collections.reverseOrder()))
                .collect(Collectors.toList());
    }

}
