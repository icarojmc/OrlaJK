package com.icaroelucas.restauranteorlajk.entities.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.cliente.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
