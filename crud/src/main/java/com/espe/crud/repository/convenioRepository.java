package com.espe.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.espe.crud.model.convenio;

@Repository
public interface convenioRepository extends CrudRepository<convenio, Long> {
	List<convenio> findById(long id);

	@Query(value = "SELECT * FROM UTIC.UZMTCONVENIO WHERE UZMTCONVENIO_ESTADO='1' ORDER BY UZMTCONVENIO_ID", nativeQuery = true)
	List<convenio> findAll();
	//@Modifying
	//@Query(value = "INSERT ALL\n"
		//	+ "    INTO utic.uzmtreqplanm(uzmtreqplanm_id,uzmtrequisito_id,uzmtplanmov_id) VALUES(id,1,1)\n"
			//+ "	INTO utic.uzmtreqplanm(uzmtreqplanm_id,uzmtrequisito_id,uzmtplanmov_id) VALUES(8,2,1)\n"
			//+ "	INTO utic.uzmtreqplanm(uzmtreqplanm_id,uzmtrequisito_id,uzmtplanmov_id) VALUES(9,3,1)\n"
			//+ "	INTO utic.uzmtreqplanm(uzmtreqplanm_id,uzmtrequisito_id,uzmtplanmov_id) VALUES(10,4,1)\n"
			//+ "SELECT * from dual;", nativeQuery = true)
//	convenio postConvenio2(Long id,convenio convenio);

}
