package com.gussoft.questions.service.impl;

import com.gussoft.questions.model.Categoria;
import com.gussoft.questions.model.Examen;
import com.gussoft.questions.repository.ExamenRepository;
import com.gussoft.questions.service.ExamenService;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Set<Examen> getAllObj() {
        return new LinkedHashSet<>(examenRepository.findAll());
    }

    @Override
    public Examen findObjById(Long id) {
        return examenRepository.findById(id).get();
    }

    @Override
    public Examen save(Examen obj) {
        return examenRepository.save(obj);
    }

    @Override
    public Examen update(Examen obj, Long id) {
        obj.setId(id);
        return examenRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        examenRepository.deleteById(id);
    }

    @Override
    public List<Examen> listTestOfCategory(Categoria obj) {
        return this.examenRepository.findByCategoria(obj);
    }

    @Override
    public List<Examen> getTestActive() {
        return examenRepository.findByActivo(true);
    }

    @Override
    public List<Examen> getTestActiveOfCategory(Categoria obj) {
        return examenRepository.findByCategoriaAndActivo(obj, true);
    }

}
