package com.gussoft.questions.service;

import com.gussoft.questions.model.Examen;
import com.gussoft.questions.model.Pregunta;
import java.util.Set;

public interface PreguntaService {

    Set<Pregunta> getAllObj();

    Pregunta findObjById(Long id);

    Pregunta save(Pregunta obj);

    Pregunta update(Pregunta obj, Long id);

    void delete(Long id);

    Set<Pregunta> getQuestionOfTest(Examen obj);

    Pregunta listQuestions(Long id);

}
