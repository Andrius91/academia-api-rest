package com.andres.trabajofinal.service.impl;

import com.andres.trabajofinal.model.Curso;
import com.andres.trabajofinal.repo.ICursoRepo;
import com.andres.trabajofinal.repo.IGenericRepo;
import com.andres.trabajofinal.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl extends CRUDImpl<Curso, Integer> implements ICursoService{

    @Autowired
    private ICursoRepo repo;
    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {
        return repo;
    }
}
