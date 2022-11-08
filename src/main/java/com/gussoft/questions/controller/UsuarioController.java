package com.gussoft.questions.controller;

import com.gussoft.questions.model.Rol;
import com.gussoft.questions.model.Usuario;
import com.gussoft.questions.model.UsuarioRol;
import com.gussoft.questions.service.UsuarioService;
import com.gussoft.questions.transfer.response.GenericResponse;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios/")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) throws Exception {
        Set<UsuarioRol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setRole("ADMINISTRADOR");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        roles.add(usuarioRol);
        Usuario data = usuarioService.save(usuario, roles);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/usuarios/{username}")
    public ResponseEntity<Usuario> getUsers(@PathVariable("username") String username) {
        Usuario data = usuarioService.getObjByUsername(username);
        if (data != null) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<GenericResponse> delete(@PathVariable("id") Long id) {
        usuarioService.deleteObj(id);
        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "OK"));
    }
}
