package com.gussoft.questions.service.impl;

import com.gussoft.questions.model.Categoria;
import com.gussoft.questions.repository.CategoriaRepository;
import com.gussoft.questions.service.CategoriaService;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Set<Categoria> getAllObj() {
        return new LinkedHashSet<>(categoriaRepository.findAll());
    }

    @Override
    public Categoria findObjById(Long id) {
        return categoriaRepository.findById(id).get();
    }

    @Override
    public Categoria save(Categoria obj) {
        return categoriaRepository.save(obj);
    }

    @Override
    public Categoria update(Categoria obj, Long id) {
        obj.setId(id);
        return categoriaRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

}
