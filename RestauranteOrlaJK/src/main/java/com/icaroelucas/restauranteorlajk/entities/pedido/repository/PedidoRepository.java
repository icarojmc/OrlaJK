package com.icaroelucas.restauranteorlajk.entities.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.pedido.model.Pedido;
import com.icaroelucas.restauranteorlajk.entities.pedido.model.Situacao;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findAllBySituacao(Situacao situacao);

}
