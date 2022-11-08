package com.gussoft.questions.repository;

import com.gussoft.questions.model.Categoria;
import com.gussoft.questions.model.Examen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

    List<Examen> findByCategoria(Categoria categoria);

    List<Examen> findByActivo(Boolean estado);

    List<Examen> findByCategoriaAndActivo(Categoria categoria,Boolean estado);

}
