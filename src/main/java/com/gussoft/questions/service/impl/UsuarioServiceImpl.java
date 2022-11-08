package com.gussoft.questions.service.impl;

import com.gussoft.questions.model.Usuario;
import com.gussoft.questions.model.UsuarioRol;
import com.gussoft.questions.repository.RolRepository;
import com.gussoft.questions.repository.UsuarioRepository;
import com.gussoft.questions.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario save(Usuario obj, Set<UsuarioRol> usuarioRols) throws Exception {
        Usuario data = usuarioRepository.findByUsername(obj.getUsername());
        if (data != null) {
            throw new Exception("El usuario ya Existe!");
        } else {
            for (UsuarioRol roles:usuarioRols) {
                rolRepository.save(roles.getRol());
            }
            obj.getUsuarioRoles().addAll(usuarioRols);
            data = usuarioRepository.save(obj);
        }
        return data;
    }

    @Override
    public Usuario getObjByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Optional<Usuario> getOneByEmail(String email) {
        return usuarioRepository.findOneByEmail(email);
    }

    @Override
    public void deleteObj(long id) {
        usuarioRepository.deleteById(id);
    }
}
