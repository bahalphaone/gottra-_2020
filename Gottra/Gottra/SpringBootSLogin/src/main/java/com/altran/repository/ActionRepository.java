package com.altran.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.altran.model.Action;

public interface ActionRepository extends JpaRepository<Action, Long>{

	@Query("select a from Action a where id_cas = :id")
	List<Action> findByUseCaseId(@Param("id") Long useCaseId);
}




