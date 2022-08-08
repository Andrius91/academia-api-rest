package com.andres.trabajofinal.controllers;

import com.andres.trabajofinal.dto.CursoDTO;
import com.andres.trabajofinal.exception.ModelNotFoundException;
import com.andres.trabajofinal.model.Curso;
import com.andres.trabajofinal.service.ICursoService;
import com.andres.trabajofinal.service.IEstudiantesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private ICursoService service;

    @Autowired
    @Qualifier("cursoMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> readAll() throws Exception {
        List<CursoDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, CursoDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Curso curso = service.readById(id);
        if(curso == null){
            throw new ModelNotFoundException("No se ha encontrado el ID: " + id);
        }
        CursoDTO CursoDTO = mapper.map(service.readById(id), CursoDTO.class);
        return new ResponseEntity<>(CursoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoDTO dto) throws Exception {
        Curso Curso = service.create(mapper.map(dto, Curso.class));

        return new ResponseEntity<>(mapper.map(Curso, CursoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CursoDTO> update(@Valid @RequestBody CursoDTO dto) throws Exception {
        Curso curso = service.readById(dto.getIdCurso());
        if(curso == null){
            throw new ModelNotFoundException("No se ha encontrado el ID: " + dto.getIdCurso());
        }
        curso = mapper.map(dto, Curso.class);
        CursoDTO dtoResponse = mapper.map(service.update(curso), CursoDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Curso curso = service.readById(id);
        if(curso == null){
            throw new ModelNotFoundException("No se ha encontrado el ID: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
