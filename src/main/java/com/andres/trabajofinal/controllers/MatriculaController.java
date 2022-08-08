package com.andres.trabajofinal.controllers;

import com.andres.trabajofinal.dto.MatriculaDTO;
import com.andres.trabajofinal.model.Estudiante;
import com.andres.trabajofinal.model.Matricula;
import com.andres.trabajofinal.service.IMatriculaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private IMatriculaService service;

    @Autowired
    @Qualifier("matriculaMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> readAll() throws Exception {
        List<MatriculaDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, MatriculaDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/relacion")
    public ResponseEntity<Map<String, List<Estudiante>>> mostrarRelacion() throws Exception {
        Map<String, List<Estudiante>> mapa = service.mostrarRelacion();

        return new ResponseEntity<>(mapa, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> readById(@PathVariable("id") Integer id) throws Exception {
        MatriculaDTO MatriculaDTO = mapper.map(service.readById(id), MatriculaDTO.class);
        return new ResponseEntity<>(MatriculaDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> create(@Valid  @RequestBody MatriculaDTO dto) throws Exception {
        Matricula m = mapper.map(dto, Matricula.class);
        Matricula matricula = service.saveTransactional(m, m.getDetalle());

        return new ResponseEntity<>(mapper.map(matricula, MatriculaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MatriculaDTO> update(@Valid @RequestBody MatriculaDTO dto) throws Exception {
        Matricula Matricula = service.readById(dto.getIdMatricula());
        if(Matricula == null){
            throw new NullPointerException("No se ha encontrado el ID: " + dto.getIdMatricula());
        }
        Matricula = mapper.map(dto, Matricula.class);
        MatriculaDTO dtoResponse = mapper.map(service.update(Matricula), MatriculaDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Matricula obj = service.readById(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
