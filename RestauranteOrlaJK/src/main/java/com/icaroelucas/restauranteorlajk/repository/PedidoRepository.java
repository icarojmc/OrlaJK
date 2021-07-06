package com.icaroelucas.restauranteorlajk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.model.Situacao;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findAllBySituacao(Situacao situacao);

}
