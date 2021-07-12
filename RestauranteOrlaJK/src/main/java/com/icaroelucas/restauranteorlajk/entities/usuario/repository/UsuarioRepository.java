package com.icaroelucas.restauranteorlajk.entities.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.usuario.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsuario(String usuario);
}
