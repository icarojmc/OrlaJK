package com.icaroelucas.restauranteorlajk.entities.alimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

}
