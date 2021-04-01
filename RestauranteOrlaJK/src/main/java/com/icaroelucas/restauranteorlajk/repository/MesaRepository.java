package com.icaroelucas.restauranteorlajk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.model.Mesa;
import com.icaroelucas.restauranteorlajk.model.Setor;
import com.icaroelucas.restauranteorlajk.model.Situacao;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>{

	List<Mesa> findAllByOcupada(boolean b);

	List<Mesa> findAllByFechada(boolean b);

	List<Mesa> findAllByOcupadaAndSetor(boolean b, Setor valueOf);

}