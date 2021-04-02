package com.icaroelucas.restauranteorlajk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.model.security.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
