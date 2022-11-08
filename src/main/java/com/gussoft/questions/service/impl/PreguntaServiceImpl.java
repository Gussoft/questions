package com.gussoft.questions.service.impl;

import com.gussoft.questions.model.Examen;
import com.gussoft.questions.model.Pregunta;
import com.gussoft.questions.repository.PreguntaRepository;
import com.gussoft.questions.service.PreguntaService;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public Set<Pregunta> getAllObj() {
        return new LinkedHashSet<>(preguntaRepository.findAll());
    }

    @Override
    public Pregunta findObjById(Long id) {
        return preguntaRepository.findById(id).get();
    }

    @Override
    public Pregunta save(Pregunta obj) {
        return preguntaRepository.save(obj);
    }

    @Override
    public Pregunta update(Pregunta obj, Long id) {
        obj.setId(id);
        return preguntaRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        preguntaRepository.deleteById(id);
    }

    @Override
    public Set<Pregunta> getQuestionOfTest(Examen obj) {
        return preguntaRepository.findByExamen(obj);
    }

    @Override
    public Pregunta listQuestions(Long id) {
        return this.preguntaRepository.getReferenceById(id);
    }

}
