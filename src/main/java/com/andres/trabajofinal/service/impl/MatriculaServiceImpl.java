package com.andres.trabajofinal.service.impl;

import com.andres.trabajofinal.model.Curso;
import com.andres.trabajofinal.model.DetalleMatricula;
import com.andres.trabajofinal.model.Estudiante;
import com.andres.trabajofinal.model.Matricula;
import com.andres.trabajofinal.repo.IGenericRepo;
import com.andres.trabajofinal.repo.IMatriculaRepo;
import com.andres.trabajofinal.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Service
public class MatriculaServiceImpl extends CRUDImpl<Matricula, Integer> implements IMatriculaService {

    @Autowired
    private IMatriculaRepo repo;

    @Override
    protected IGenericRepo<Matricula, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Matricula saveTransactional(Matricula matricula, List<DetalleMatricula> details) {
        details.forEach(d -> d.setMatricula(matricula));
        matricula.setDetalle(details);
        return repo.save(matricula);
    }

    @Override
    public Map<String, List<Estudiante>> mostrarRelacion() {
        Stream<List<DetalleMatricula>> detalles = repo.findAll().stream().map(Matricula::getDetalle);

        Stream<DetalleMatricula> detalle = detalles.flatMap(Collection::stream);

        Map<String, List<Estudiante>> map = detalle
                .collect(groupingBy(d-> d.getCurso().getNombre(),
                        Collectors.mapping(x-> x.getMatricula().getEstudiante(), Collectors.toList())));

        return map;
    }
}
