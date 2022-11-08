package com.gussoft.questions.service;

import com.gussoft.questions.model.Categoria;
import java.util.Set;

public interface CategoriaService {

    Set<Categoria> getAllObj();

    Categoria findObjById(Long id);

    Categoria save(Categoria obj);

    Categoria update(Categoria obj, Long id);

    void delete(Long id);

}
