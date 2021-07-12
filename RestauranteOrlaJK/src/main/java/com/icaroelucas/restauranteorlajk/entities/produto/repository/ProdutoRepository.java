package com.icaroelucas.restauranteorlajk.entities.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.produto.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
