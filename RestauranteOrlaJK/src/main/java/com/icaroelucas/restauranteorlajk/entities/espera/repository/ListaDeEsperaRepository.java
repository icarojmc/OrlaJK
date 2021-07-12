package com.icaroelucas.restauranteorlajk.entities.espera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.espera.model.ListaDeEspera;

@Repository
public interface ListaDeEsperaRepository extends JpaRepository<ListaDeEspera, Long> {

}
