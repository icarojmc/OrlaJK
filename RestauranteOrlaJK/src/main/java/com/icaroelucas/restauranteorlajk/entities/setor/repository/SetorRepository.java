package com.icaroelucas.restauranteorlajk.entities.setor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;
import com.icaroelucas.restauranteorlajk.entities.setor.model.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

}
