package com.gussoft.questions.repository;

import com.gussoft.questions.model.Examen;
import com.gussoft.questions.model.Pregunta;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    Set<Pregunta> findByExamen(Examen examen);

}
