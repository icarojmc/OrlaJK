package com.icaroelucas.restauranteorlajk.entities.alimento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.alimento.model.Alimento;
import com.icaroelucas.restauranteorlajk.entities.alimento.model.Tipo;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

	List<Alimento> findAllByTipo(Tipo tipo);

}
