package com.espe.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.espe.crud.model.DocenteExterno;

@Repository
public interface docenteExternoRepository extends CrudRepository<DocenteExterno, Long> {
	List<DocenteExterno> findById(long id);

	@Query(value = "SELECT * FROM UTIC.UZMTDOCENTEXTER ORDER BY UZMTDOCENTEXTER_ID", nativeQuery = true)
	List<DocenteExterno> findAll();
	
}
