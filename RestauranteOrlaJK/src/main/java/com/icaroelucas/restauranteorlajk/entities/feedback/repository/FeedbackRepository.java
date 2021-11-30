package com.icaroelucas.restauranteorlajk.entities.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.icaroelucas.restauranteorlajk.entities.feedback.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	@Query(value = "select avg(nota_atendimento) from feedback;", nativeQuery = true)
	Double avgNotaAtendimento();
	
	@Query(value = "select avg(nota_comida) from feedback;", nativeQuery = true)
	Double avgNotaComida();
	
	@Query(value = "select avg(nota_estrutura) from feedback;", nativeQuery = true)
	Double avgNotaEstrutura();
	
	@Query(value = "select count(*) from feedback", nativeQuery = true)
	Integer countFeedback();
	
	@Query(value = "select avg(nota_atendimento) from feedback where dia > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	Double avgNotaAtendimentoSemana();
	
	@Query(value = "select avg(nota_comida) from feedback where dia > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	Double avgNotaComidaSemana();
	
	@Query(value = "select avg(nota_estrutura) from feedback where dia > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	Double avgNotaEstruturaSemana();
	
	@Query(value = "select count(*) from feedback where dia > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	Integer countFeedbackSemana();
	
	
	
}
