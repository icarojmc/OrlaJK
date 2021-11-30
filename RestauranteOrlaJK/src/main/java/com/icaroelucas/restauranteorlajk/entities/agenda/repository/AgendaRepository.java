package com.icaroelucas.restauranteorlajk.entities.agenda.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.agenda.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	List<Agenda> findBydataEntradaBetween(LocalDate now, LocalDate plusDays);

	List<Agenda> findByDataEntrada(LocalDate dia);

}
