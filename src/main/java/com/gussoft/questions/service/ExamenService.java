package com.gussoft.questions.service;

import com.gussoft.questions.model.Categoria;
import com.gussoft.questions.model.Examen;

import java.util.List;
import java.util.Set;

public interface ExamenService {

    Set<Examen> getAllObj();

    Examen findObjById(Long id);

    Examen save(Examen obj);

    Examen update(Examen obj, Long id);

    void delete(Long id);

    List<Examen> listTestOfCategory(Categoria obj);

    List<Examen> getTestActive();

    List<Examen> getTestActiveOfCategory(Categoria obj);

}
