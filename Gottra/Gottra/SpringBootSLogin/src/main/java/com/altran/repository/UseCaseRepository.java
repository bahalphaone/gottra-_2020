package com.altran.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.altran.model.UseCase;

public interface UseCaseRepository extends JpaRepository<UseCase, Long>{

	@Query("select u from UseCase u where id_fichier = :id")
	List<UseCase> findByFileId(@Param("id") Long fileId);
}




