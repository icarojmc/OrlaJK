package com.icaroelucas.restauranteorlajk.entities.debito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.debito.model.Debito;

@Repository
public interface DebitoRepository extends JpaRepository<Debito, Long>{

}
