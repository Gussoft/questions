package com.gussoft.questions.repository;

import com.gussoft.questions.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    Optional<Usuario> findOneByEmail(String email);

}
