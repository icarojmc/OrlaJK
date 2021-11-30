package com.icaroelucas.restauranteorlajk.entities.registrodiario.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.registrodiario.model.RegistroDiario;

@Repository
public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Long> {

	Page<RegistroDiario> findAllByDataDoRegistro(Pageable pageable, LocalDate dia);

}
