package com.icaroelucas.restauranteorlajk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
