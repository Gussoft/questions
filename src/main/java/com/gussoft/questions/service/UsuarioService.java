package com.gussoft.questions.service;

import com.gussoft.questions.model.Usuario;
import com.gussoft.questions.model.UsuarioRol;

import java.util.Optional;
import java.util.Set;

public interface UsuarioService {

    Usuario save(Usuario obj, Set<UsuarioRol> usuarioRols) throws Exception;

    Usuario getObjByUsername(String username);

    Optional<Usuario> getOneByEmail(String email);

    void deleteObj(long id);

}
