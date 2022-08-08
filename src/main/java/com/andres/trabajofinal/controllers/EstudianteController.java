package com.andres.trabajofinal.controllers;

import com.andres.trabajofinal.dto.EstudianteDTO;
import com.andres.trabajofinal.model.Estudiante;
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
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private IEstudiantesService service;

    @Autowired
    @Qualifier("estudianteMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> readAll() throws Exception {
        List<EstudianteDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, EstudianteDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> readById(@PathVariable("id") Integer id) throws Exception {
        EstudianteDTO estudianteDTO = mapper.map(service.readById(id), EstudianteDTO.class);
        return new ResponseEntity<>(estudianteDTO, HttpStatus.OK);
    }
    @GetMapping("/listaDesc")
    public ResponseEntity<List<EstudianteDTO>> listaDesc() throws Exception {
        List<EstudianteDTO> list = service.listaDesc().stream()
                .map(c -> mapper.map(c, EstudianteDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@Valid @RequestBody EstudianteDTO dto) throws Exception {
        Estudiante estudiante = service.create(mapper.map(dto, Estudiante.class));

        return new ResponseEntity<>(mapper.map(estudiante, EstudianteDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EstudianteDTO> update(@Valid @RequestBody EstudianteDTO dto) throws Exception {
        Estudiante estudiante = service.readById(dto.getIdEstudiante());
        if(estudiante == null){
            throw new NullPointerException("No se ha encontrado el ID: " + dto.getIdEstudiante());
        }
        estudiante = mapper.map(dto, Estudiante.class);
        EstudianteDTO dtoResponse = mapper.map(service.update(estudiante), EstudianteDTO.class);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        Estudiante obj = service.readById(id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
