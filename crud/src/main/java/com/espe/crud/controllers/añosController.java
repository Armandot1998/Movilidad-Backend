package com.espe.crud.controllers;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.espe.crud.model.Años;
import com.espe.crud.model.Sanciones;
import com.espe.crud.model.Escalafonados;
import com.espe.crud.model.tipomovilidad;
import com.espe.crud.vo.PMovilidad;
import com.espe.crud.vo.PlanMovilidadVo;
import com.espe.crud.vo.ReqmovsubmVo;
import com.espe.crud.vo.ReqplanmVo;
import com.espe.crud.vo.SMovilidad;
import com.espe.crud.vo.SolicitudMovilidadVo;
import com.espe.crud.vo.convenioVo;
import com.espe.crud.vo.verificacionvo;


@CrossOrigin(origins = "*")
@RestController 
public class añosController {

    public static final Logger logger= LoggerFactory.getLogger(añosController.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@GetMapping("/años/{id}")
	public List<Años> findbyPIDM(@PathVariable Long id) throws SQLException{
		String q = "SELECT TRUNC((( SYSDATE - PEBEMPL_FIRST_HIRE_DATE )/365),0) AS TOTAL FROM PEBEMPL WHERE PEBEMPL_PIDM = " + id +" AND PEBEMPL_BCAT_CODE = 'DO'";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(Años.class));
	}
	
	@GetMapping("/pmov")
	public List<PMovilidad> findPM() throws SQLException{
		String q = "SELECT UTIC.uzmtplanmov.uzmtplanmov_id,UTIC.uzmtplanmov.uzmtplanmov_nombre, "
				+ "utic.uzmttipmov.uzmtipmov_nombre  FROM UTIC.uzmtplanmov INNER JOIN \r\n" + 
				"UTIC.UZMTTIPMOV on UTIC.uzmtplanmov.UZMTIPMOV_ID= UTIC.UZMTTIPMOV.UZMTIPMOV_ID\r\n" + 
				"ORDER BY UZMTPLANMOV_ID ";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(PMovilidad.class));
	}
	
	@GetMapping("/smovfind")
	public List<SMovilidad> findS() throws SQLException{
		String q = "SELECT UTIC.uzmtmovsubm.uzmtmovsubm_id,UTIC.uzmtmovsubm.uzmtmovsubm_nom,utic.uzmttipmov.uzmtipmov_nombre  FROM UTIC.uzmtmovsubm INNER JOIN \r\n" + 
				"UTIC.UZMTTIPMOV on UTIC.uzmtmovsubm.UZMTIPMOV_ID= UTIC.UZMTTIPMOV.UZMTIPMOV_ID\r\n" + 
				"ORDER BY UZMTMOVSUBM_ID";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(SMovilidad.class));
	}
	
	@GetMapping("/conven")
	public List<convenioVo> findC() throws SQLException{
		String q = "SELECT UTIC.uzmtconvenio.uzmtconvenio_id,utic.uzmttipconve.uzmttipconve_nom,utic.uzmtconvenio.uzmtconvenio_fech_ini,utic.uzmtconvenio.uzmtconvenio_fech_fin,utic.uzmtconvenio.uzmtconvenio_estado,utic.uzmtconvenio.stusbgi_code\r\n" + 
				"FROM UTIC.UZMTCONVENIO INNER JOIN UTIC.UZMTTIPCONVE on UTIC.UZMTCONVENIO.UZMTCONVENIO_ID = UTIC.UZMTTIPCONVE.UZMTTIPCONVE_ID \r\n" + 
				"ORDER BY UZMTCONVENIO_ID";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(convenioVo.class));
	}
	
	@GetMapping("/smov")
	public List<SMovilidad> findSM() throws SQLException{
		String q = "SELECT UTIC.uzmtmovsubm.uzmtmovsubm_id,UTIC.uzmtmovsubm.uzmtmovsubm_nom,utic.uzmttipmov.uzmtipmov_nombre  FROM UTIC.uzmtmovsubm INNER JOIN \r\n" + 
				"UTIC.UZMTTIPMOV on UTIC.uzmtmovsubm.UZMTIPMOV_ID= UTIC.UZMTTIPMOV.UZMTIPMOV_ID\r\n" + 
				"ORDER BY UZMTMOVSUBM_ID ";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(SMovilidad.class));
	}
	@GetMapping("/escalafon/{id}")
	public List<Escalafonados> findbyPIDM2(@PathVariable Long id) throws SQLException{
		String q = "(SELECT DISTINCT\r\n" + 
				"(select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON\r\n" + 
				"FROM PEBEMPL r, PERAPPT\r\n" + 
				"WHERE\r\n" + 
				"r.pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND r.pebempl_empl_status = 'A'\r\n" + 
				"--AND( r.pebempl_bcat_code = 'DO' )\r\n" + 
				"AND r.pebempl_PIDM = "+ id + "\r\n" + 
				"AND (r.pebempl_bcat_code = 'DO' or r.pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"    r.pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM\r\n" + 
				"    AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )   AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date)\r\n" + 
				"    FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM AND   (nbrjobs_pict_code = 'ED'\r\n" + 
				"    or nbrjobs_pict_code = 'LD' )))\r\n" + 
				"AND PERAPPT_APPT_EFF_DATE =     (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = " + id + ")\r\n" + 
				") \r\n" + 
				"";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(Escalafonados.class));
	}
	
	
	
	@GetMapping("/nuevo2/{id}")
	public List<verificacionvo> find2(@PathVariable Long id) throws SQLException{
		String q = "select  DISTINCT(uzmtrequisito_detalle) as nombre, uzmtverireq_estado, uzmtverireq_id from utic.uzmtverireq,utic.uzmtrequisito,utic.uzmtreqplanm where utic.uzmtverireq.uzmtreqplanm_id= utic.uzmtreqplanm.uzmtreqplanm_id and utic.uzmtrequisito.uzmtrequisito_id= 1 and peaempl_pidm="+id+"  and rownum < 2 order by uzmtverireq_id ";

	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(verificacionvo.class));
	}
	
	
	@GetMapping("/plan")
	public List<tipomovilidad> plan() throws SQLException{
		String q = "SELECT DISTINCT(UZMTIPMOV_NOMBRE) AS movilidad FROM UTIC.UZMTCONVO,UTIC.UZMTTIPMOV WHERE UZMTTIPMOV.UZMTCONVO_ID= UZMTCONVO.UZMTCONVO_ID and UZMTCONVO_ESTADO='1' ORDER BY UZMTIPMOV_NOMBRE\r\n" + 
				"";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(tipomovilidad.class));
	}
	
	@GetMapping("/planmovilidad")
	public List<PlanMovilidadVo> planmovilidad() throws SQLException{
		String q = "SELECT DISTINCT(UZMTPLANMOV_NOMBRE) AS planmovilidad FROM UTIC.UZMTCONVO,UTIC.UZMTPLANMOV,UTIC.UZMTTIPMOV WHERE UZMTPLANMOV.UZMTIPMOV_ID= UZMTTIPMOV.UZMTIPMOV_ID and UZMTTIPMOV.UZMTCONVO_ID= UZMTCONVO.UZMTCONVO_ID and UZMTCONVO_ESTADO='1' ORDER BY UZMTPLANMOV_NOMBRE";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(PlanMovilidadVo.class));
	}
	
	@GetMapping("/indexadasmovilidad")
	public List<SolicitudMovilidadVo> indexadassolicitud() throws SQLException{
		String q = "SELECT DISTINCT(UZMTMOVSUBM_NOM) AS solicitudmovilidad FROM UTIC.UZMTCONVO,UTIC.UZMTMOVSUBM,UTIC.UZMTTIPMOV WHERE UZMTMOVSUBM.UZMTIPMOV_ID= UZMTTIPMOV.UZMTIPMOV_ID and UZMTTIPMOV.UZMTCONVO_ID= UZMTCONVO.UZMTCONVO_ID and UZMTCONVO_ESTADO='1' ORDER BY UZMTMOVSUBM_NOM";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(SolicitudMovilidadVo.class));
	}	
	
	
	
	
	@GetMapping("/requisitoAños/{id}")
	public List<ReqplanmVo> requisitosAños(@PathVariable Long id) throws SQLException{
		String q = "INSERT INTO utic.uzmtverireq( uzmtreqplanm_id, PEAEMPL_PIDM,uzmtverireq_estado)\n" + 
				"( SELECT 3,PEBEMPL_PIDM,1  FROM PEBEMPL WHERE  PEBEMPL_PIDM ="+id+" AND PEBEMPL_BCAT_CODE = 'DO' AND \n" + 
				"(SELECT TRUNC((( SYSDATE - PEBEMPL_FIRST_HIRE_DATE )/365),0) FROM PEBEMPL WHERE PEBEMPL_PIDM ="+id+" AND PEBEMPL_BCAT_CODE = 'DO') > 3)";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
		
		
	}
	
	
	
	@GetMapping("/requisitoAños2/{id}")
	public List<ReqplanmVo> requisitoAños2(@PathVariable Long id) throws SQLException{
		String q = "insert into utic.uzmtverireq(uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado)\r\n" + 
				"select 3,"+ id +",0 from dual where not exists (select uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado from utic.uzmtverireq where\r\n" + 
				"(uzmtverireq_estado= 1 and uzmtreqplanm_id= 3 and peaempl_pidm= "+ id +"))";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}

	
	
	
	
	
	
	@GetMapping("/requisito/{id}")
	public List<ReqplanmVo> requisito(@PathVariable Long id) throws SQLException{
		String q = "INSERT \r\n" + 
				"WHEN \r\n" + 
				"(SELECT TRUNC((( SYSDATE - PEBEMPL_FIRST_HIRE_DATE )/365),0) AS \r\n" + 
				"TOTAL FROM PEBEMPL WHERE PEBEMPL_PIDM = "+ id +" AND PEBEMPL_BCAT_CODE = 'DO') >= 3\r\n" + 
				"THEN\r\n" + 
				"into utic.uzmtverireq ( uzmtreqplanm_id, PEAEMPL_PIDM, utic.uzmtverireq.uzmtverireq_estado) VALUES (3, "+ id +" ,1)\r\n" + 
				"WHEN\r\n" + 
				"(SELECT TRUNC((( SYSDATE - PEBEMPL_FIRST_HIRE_DATE )/365),0) AS \r\n" + 
				"TOTAL FROM PEBEMPL WHERE PEBEMPL_PIDM = "+ id +" AND PEBEMPL_BCAT_CODE = 'DO') < 3\r\n" + 
				"THEN\r\n" + 
				"into utic.uzmtverireq ( uzmtreqplanm_id, PEAEMPL_PIDM, utic.uzmtverireq.uzmtverireq_estado) VALUES (3, "+ id +" , 0)\r\n" + 
				"WHEN\r\n" + 
				"(SELECT DISTINCT (select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON FROM PEBEMPL , PERAPPT WHERE pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND pebempl_empl_status = 'A' AND (pebempl_bcat_code = 'DO' ) AND pebempl_PIDM ="+ id +" AND (pebempl_bcat_code = 'DO' or pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = pebempl_PIDM  AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )  AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date) \r\n" + 
				"FROM NBRJOBS where NBRJOBS_pidm = pebempl_PIDM AND   (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' ))) AND PERAPPT_APPT_EFF_DATE = (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +")\r\n" + 
				") LIKE 'TITULAR%'\r\n" + 
				"THEN\r\n" + 
				"into utic.uzmtverireq ( uzmtreqplanm_id, PEAEMPL_PIDM, utic.uzmtverireq.uzmtverireq_estado) VALUES (1, "+ id +", 1)\r\n" + 
				"WHEN\r\n" + 
				"(SELECT DISTINCT (select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON FROM PEBEMPL , PERAPPT WHERE pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND pebempl_empl_status = 'A' AND (pebempl_bcat_code = 'DO' ) AND pebempl_PIDM = "+ id +" AND (pebempl_bcat_code = 'DO' or pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = pebempl_PIDM  AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )  AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date) \r\n" + 
				"FROM NBRJOBS where NBRJOBS_pidm = pebempl_PIDM AND   (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' ))) AND PERAPPT_APPT_EFF_DATE = (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +")\r\n" + 
				") NOT LIKE 'TITULAR%'\r\n" + 
				"THEN\r\n" + 
				"into utic.uzmtverireq ( uzmtreqplanm_id, PEAEMPL_PIDM, utic.uzmtverireq.uzmtverireq_estado) VALUES (1,"+ id +" , 0)\r\n" + 
				"WHEN \r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D04','S04') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 2 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D05','S05') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D06','S06') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D08') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D09') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D010') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D011') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D012') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1\r\n" + 
				"THEN\r\n" + 
				"into utic.uzmtverireq ( uzmtreqplanm_id, PEAEMPL_PIDM, utic.uzmtverireq.uzmtverireq_estado) VALUES (4, "+ id +", 0)\r\n" + 
				" WHEN \r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D04','S04') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D05','S05') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D06','S06') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D08') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2  AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D09') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D010') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D011') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D012') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2\r\n" + 
				"THEN\r\n" + 
				"into utic.uzmtverireq ( uzmtreqplanm_id, PEAEMPL_PIDM, utic.uzmtverireq.uzmtverireq_estado) VALUES (4, "+ id +", 1)" + 
				
				"SELECT * FROM DUAL\r\n" + 
				"";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	
	@GetMapping("/mostrarRequisitos/{id}")
	public List<verificacionvo> find(@PathVariable Long id) throws SQLException{
		String q = "\r\n" + 
				"select  DISTINCT(uzmtrequisito_detalle) as nombre, uzmtverireq_estado, uzmtverireq_id, utic.uzmtreqplanm.uzmtreqplanm_id\r\n" + 
				"from utic.uzmtverireq,utic.uzmtrequisito,utic.uzmtreqplanm where utic.uzmtverireq.uzmtreqplanm_id= utic.uzmtreqplanm.uzmtreqplanm_id \r\n" + 
				"and utic.uzmtrequisito.uzmtrequisito_id= utic.uzmtreqplanm.uzmtrequisito_id \r\n" + 
				"and peaempl_pidm ="+ id +" and utic.uzmtverireq.uzmtreqplanm_id = 1 and rownum = 1\r\n" + 
				"\r\n" + 
				"UNION\r\n" + 
				"\r\n" + 
				"select  DISTINCT(uzmtrequisito_detalle) as nombre, uzmtverireq_estado, uzmtverireq_id, utic.uzmtreqplanm.uzmtreqplanm_id\r\n" + 
				"from utic.uzmtverireq,utic.uzmtrequisito,utic.uzmtreqplanm where utic.uzmtverireq.uzmtreqplanm_id= utic.uzmtreqplanm.uzmtreqplanm_id \r\n" + 
				"and utic.uzmtrequisito.uzmtrequisito_id= utic.uzmtreqplanm.uzmtrequisito_id\r\n" + 
				"and peaempl_pidm = "+ id +" and utic.uzmtverireq.uzmtreqplanm_id = 2 and rownum = 1\r\n" + 
				"\r\n" + 
				"UNION\r\n" + 
				"\r\n" + 
				"select  DISTINCT(uzmtrequisito_detalle) as nombre, uzmtverireq_estado, uzmtverireq_id, utic.uzmtreqplanm.uzmtreqplanm_id\r\n" + 
				"from utic.uzmtverireq,utic.uzmtrequisito,utic.uzmtreqplanm where utic.uzmtverireq.uzmtreqplanm_id= utic.uzmtreqplanm.uzmtreqplanm_id \r\n" + 
				"and utic.uzmtrequisito.uzmtrequisito_id= utic.uzmtreqplanm.uzmtrequisito_id\r\n" + 
				"and peaempl_pidm ="+ id +" and utic.uzmtverireq.uzmtreqplanm_id = 3 and rownum = 1\r\n" + 
				"\r\n" + 
				"UNION\r\n" + 
				"\r\n" + 
				"select  DISTINCT(uzmtrequisito_detalle) as nombre, uzmtverireq_estado, uzmtverireq_id, utic.uzmtreqplanm.uzmtreqplanm_id\r\n" + 
				"from utic.uzmtverireq,utic.uzmtrequisito,utic.uzmtreqplanm where utic.uzmtverireq.uzmtreqplanm_id= utic.uzmtreqplanm.uzmtreqplanm_id \r\n" + 
				"and utic.uzmtrequisito.uzmtrequisito_id= utic.uzmtreqplanm.uzmtrequisito_id\r\n" + 
				"and peaempl_pidm ="+ id +" and utic.uzmtverireq.uzmtreqplanm_id = 4 and rownum = 1\r\n" + 
				"\r\n" + 
				"order by uzmtreqplanm_id";
	System.out.println(q);
		return jdbcTemplate.query(q, new BeanPropertyRowMapper<>(verificacionvo.class));
	}
	
	@GetMapping("/requisitomovsubm/{id}")
	public List<ReqmovsubmVo> requisitomovssubm(@PathVariable Long id) throws SQLException{
		String q ="insert WHEN ((SELECT DISTINCT\r\n" + 
				"(select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON\r\n" + 
				"FROM PEBEMPL r, PERAPPT\r\n" + 
				"WHERE\r\n" + 
				"r.pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND r.pebempl_empl_status = 'A'\r\n" + 
				"--AND( r.pebempl_bcat_code = 'DO' )\r\n" + 
				"AND r.pebempl_PIDM = "+ id +"\r\n" + 
				"AND (r.pebempl_bcat_code = 'DO' or r.pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"    r.pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM\r\n" + 
				"    AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )   AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date)\r\n" + 
				"    FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM AND   (nbrjobs_pict_code = 'ED'\r\n" + 
				"    or nbrjobs_pict_code = 'LD' )))\r\n" + 
				"AND PERAPPT_APPT_EFF_DATE =     (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +")\r\n" + 
				") )  = (('TITULAR AUXILIAR 1'))  THEN\r\n" + 
				" into utic.uzmtverireq(uzmtverireq_id, uzmtreqmovsubm_id, PEAEMPL_PIDM) VALUES (11,1,"+ id +")\r\n" + 
				" WHEN ((SELECT DISTINCT\r\n" + 
				"(select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON\r\n" + 
				"FROM PEBEMPL r, PERAPPT\r\n" + 
				"WHERE\r\n" + 
				"r.pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND r.pebempl_empl_status = 'A'\r\n" + 
				"--AND( r.pebempl_bcat_code = 'DO' )\r\n" + 
				"AND r.pebempl_PIDM = "+id+"\r\n" + 
				"AND (r.pebempl_bcat_code = 'DO' or r.pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"    r.pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM\r\n" + 
				"    AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )   AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date)\r\n" + 
				"    FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM AND   (nbrjobs_pict_code = 'ED'\r\n" + 
				"    or nbrjobs_pict_code = 'LD' )))\r\n" + 
				"AND PERAPPT_APPT_EFF_DATE =     (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +")\r\n" + 
				") )  = (('TITULAR PRINCIPAL 1'))  THEN\r\n" + 
				" into utic.uzmtverireq(uzmtverireq_id, uzmtreqmovsubm_id, PEAEMPL_PIDM) VALUES (11,1,"+ id +")\r\n" + 
				" WHEN ((SELECT DISTINCT\r\n" + 
				"(select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON\r\n" + 
				"FROM PEBEMPL r, PERAPPT\r\n" + 
				"WHERE\r\n" + 
				"r.pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND r.pebempl_empl_status = 'A'\r\n" + 
				"--AND( r.pebempl_bcat_code = 'DO' )\r\n" + 
				"AND r.pebempl_PIDM = "+ id +"\r\n" + 
				"AND (r.pebempl_bcat_code = 'DO' or r.pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"    r.pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM\r\n" + 
				"    AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )   AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date)\r\n" + 
				"    FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM AND   (nbrjobs_pict_code = 'ED'\r\n" + 
				"    or nbrjobs_pict_code = 'LD' )))\r\n" + 
				"AND PERAPPT_APPT_EFF_DATE =     (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +")\r\n" + 
				") )  = (('TITULAR AGREGADO 3'))  THEN\r\n" + 
				" into utic.uzmtverireq(uzmtverireq_id, uzmtreqmovsubm_id, PEAEMPL_PIDM) VALUES (11,1,"+ id +")\r\n" + 
				" WHEN ((SELECT DISTINCT\r\n" + 
				"(select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON\r\n" + 
				"FROM PEBEMPL r, PERAPPT\r\n" + 
				"WHERE\r\n" + 
				"r.pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND r.pebempl_empl_status = 'A'\r\n" + 
				"--AND( r.pebempl_bcat_code = 'DO' )\r\n" + 
				"AND r.pebempl_PIDM = "+ id +"\r\n" + 
				"AND (r.pebempl_bcat_code = 'DO' or r.pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"    r.pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM\r\n" + 
				"    AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )   AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date)\r\n" + 
				"    FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM AND   (nbrjobs_pict_code = 'ED'\r\n" + 
				"    or nbrjobs_pict_code = 'LD' )))\r\n" + 
				"AND PERAPPT_APPT_EFF_DATE =     (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +")\r\n" + 
				") )  = (('TITULAR AGREGADO 1'))  THEN\r\n" + 
				" into utic.uzmtverireq(uzmtverireq_id, uzmtreqmovsubm_id, PEAEMPL_PIDM) VALUES (11,1,"+ id +")\r\n" + 
				" WHEN ((SELECT DISTINCT\r\n" + 
				"(select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON\r\n" + 
				"FROM PEBEMPL r, PERAPPT\r\n" + 
				"WHERE\r\n" + 
				"r.pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND r.pebempl_empl_status = 'A'\r\n" + 
				"--AND( r.pebempl_bcat_code = 'DO' )\r\n" + 
				"AND r.pebempl_PIDM = "+ id +"\r\n" + 
				"AND (r.pebempl_bcat_code = 'DO' or r.pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"    r.pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM\r\n" + 
				"    AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )   AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date)\r\n" + 
				"    FROM NBRJOBS where NBRJOBS_pidm = r.pebempl_PIDM AND   (nbrjobs_pict_code = 'ED'\r\n" + 
				"    or nbrjobs_pict_code = 'LD' )))\r\n" + 
				"AND PERAPPT_APPT_EFF_DATE =     (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +")\r\n" + 
				") )  = (('TITULAR AGREGADO 2') )  THEN\r\n" + 
				" into utic.uzmtverireq(uzmtverireq_id, uzmtreqmovsubm_id, PEAEMPL_PIDM) VALUES (11,1,"+ id +")\r\n" + 
				" select uzmtverireq_id, uzmtreqmovsubm_id, PEAEMPL_PIDM from utic.uzmtverireq";
	System.out.println(q);
		return (List<ReqmovsubmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqmovsubmVo.class));
	}
	@GetMapping("/reqmov1/{id}")
	public List<ReqmovsubmVo> requisitomov(@PathVariable Long id) throws SQLException{
		String q = "insert into utic.uzmtverireq(uzmtverireq_id, uzmtreqmovsubm_id, PEAEMPL_PIDM, uzmtverireq_estado)\r\n" + 
				"select 11,1,"+ id +",0 from dual where not exists (select uzmtverireq_id, uzmtreqmovsubm_id,"
						+ " PEAEMPL_PIDM, uzmtverireq_estado from utic.uzmtverireq where\r\n" + 
				"(uzmtverireq_estado= 1 and uzmtreqmovsubm_id= 1 and peaempl_pidm= "+ id +"))";
	System.out.println(q);
		return (List<ReqmovsubmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqmovsubmVo.class));
	}
	
	@GetMapping("/req1/{id}")
	public List<ReqplanmVo> requisito1(@PathVariable Long id) throws SQLException{
		String q = "insert into utic.uzmtverireq(uzmtverireq_id, uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado)\r\n" + 
				"select 7,1,"+ id +",0 from dual where not exists (select uzmtverireq_id, uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado from utic.uzmtverireq where\r\n" + 
				"(uzmtverireq_estado= 1 and uzmtreqplanm_id= 1 and peaempl_pidm= "+ id +"))";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	@GetMapping("/req2/{id}")
	public List<ReqplanmVo> requisito2(@PathVariable Long id) throws SQLException{
		String q = "insert into utic.uzmtverireq(uzmtverireq_id, uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado)\r\n" + 
				"select 8,1,"+ id +",0 from dual where not exists (select uzmtverireq_id, uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado from utic.uzmtverireq where\r\n" + 
				"(uzmtverireq_estado= 1 and uzmtreqplanm_id= 2 and peaempl_pidm= "+ id +"))";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	@GetMapping("/req3/{id}")
	public List<ReqplanmVo> requisito3(@PathVariable Long id) throws SQLException{
		String q = "insert into utic.uzmtverireq(uzmtverireq_id, uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado)\r\n" + 
				"select 9,1,"+ id +",0 from dual where not exists (select uzmtverireq_id, uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado from utic.uzmtverireq where\r\n" + 
				"(uzmtverireq_estado= 1 and uzmtreqplanm_id= 3 and peaempl_pidm= "+ id +"))";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	
	
	@GetMapping("/req4/{id}")
	public List<ReqplanmVo> requisito4(@PathVariable Long id) throws SQLException{
		String q = "insert into utic.uzmtverireq(uzmtverireq_id, uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado)\r\n" + 
				"select 10,1,"+ id +",0 from dual where not exists (select uzmtverireq_id, uzmtreqplanm_id, PEAEMPL_PIDM, uzmtverireq_estado from utic.uzmtverireq where\r\n" + 
				"(uzmtverireq_estado= 1 and uzmtreqplanm_id= 4 and peaempl_pidm= "+ id +"))";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	
	
	
	@GetMapping("/sm/{id}")
	public List<ReqplanmVo> smovilidad(@PathVariable Long id) throws SQLException{
		String q = "insert when (select sum(uzmtreqplanm_id) from utic.uzmtverireq where PEAEMPL_PIDM= "+ id +" and uzmtverireq_estado=1)=3 THEN\r\n" + 
				"into utic.uzmtsolictmov(uzmtsolictmov_id,uzmtverireq_id,uzmtsolictmov_estado,uzmtsolictmov_obser) VALUES('2','1','1','Completado')\r\n" + 
				"select uzmtsolictmov_id,uzmtverireq_id,uzmtsolictmov_estado,uzmtsolictmov_obser from utic.uzmtsolictmov\r\n" + 
				"";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	
	
	@GetMapping("/Escalafonado/{id}")
	public List<ReqplanmVo> requisito1a(@PathVariable Long id) throws SQLException{
		String q = "INSERT INTO utic.uzmtverireq( uzmtreqplanm_id, PEAEMPL_PIDM, UZMTVERIREQ_ESTADO)\r\n" + 
				"( SELECT 1,PEBEMPL_PIDM, 1   FROM PEBEMPL WHERE  PEBEMPL_PIDM ="+ id +" AND PEBEMPL_BCAT_CODE = 'DO' AND \r\n" + 
				"(SELECT DISTINCT (select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON FROM PEBEMPL , PERAPPT WHERE pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND pebempl_empl_status = 'A' AND (pebempl_bcat_code = 'DO' ) AND pebempl_PIDM ="+ id +" AND (pebempl_bcat_code = 'DO' or pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = pebempl_PIDM  AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )  AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date) \r\n" + 
				"FROM NBRJOBS where NBRJOBS_pidm = pebempl_PIDM AND   (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' ))) AND PERAPPT_APPT_EFF_DATE = (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +" )\r\n" + 
				") LIKE 'TITULAR%' )";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	
	@GetMapping("/Escalafonado2/{id}")
	public List<ReqplanmVo> requisito1b(@PathVariable Long id) throws SQLException{
		String q = "INSERT INTO utic.uzmtverireq( uzmtreqplanm_id, PEAEMPL_PIDM, UZMTVERIREQ_ESTADO)\r\n" + 
				"( SELECT 1,PEBEMPL_PIDM, 1   FROM PEBEMPL WHERE  PEBEMPL_PIDM ="+ id +" AND PEBEMPL_BCAT_CODE = 'DO' AND \r\n" + 
				"(SELECT DISTINCT (select max(ptrtenr_desc)  from PTRTENR where ptrtenr_code= PERAPPT_TENURE_CODE ) as CATEGORIA_ESCALAFON FROM PEBEMPL , PERAPPT WHERE pebempl_PIDM = PERAPPT.PERAPPT_PIDM\r\n" + 
				"AND pebempl_empl_status = 'A' AND (pebempl_bcat_code = 'DO' ) AND pebempl_PIDM ="+ id +" AND (pebempl_bcat_code = 'DO' or pebempl_bcat_code = 'SP' ) AND\r\n" + 
				"pebempl_empl_status = (SELECT MAX(NBRJOBS_STATUS) FROM NBRJOBS where NBRJOBS_pidm = pebempl_PIDM  AND (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' )  AND nbrjobs_effective_date = (SELECT MAX(nbrjobs_effective_date) \r\n" + 
				"FROM NBRJOBS where NBRJOBS_pidm = pebempl_PIDM AND   (nbrjobs_pict_code = 'ED' or nbrjobs_pict_code = 'LD' ))) AND PERAPPT_APPT_EFF_DATE = (select MAX(PERAPPT_APPT_EFF_DATE)  from PERAPPT WHERE PERAPPT_PIDM = "+ id +" )\r\n" + 
				") NOT LIKE 'TITULAR%' )";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	

	
	
	@GetMapping("/GetRequisito1/{id}")
	public List<ReqplanmVo> requisitoc(@PathVariable Long id) throws SQLException{
		String q = "select  DISTINCT(uzmtrequisito_detalle) as nombre, uzmtverireq_estado, uzmtverireq_id \r\n" + 
				"from utic.uzmtverireq,utic.uzmtrequisito,utic.uzmtreqplanm where utic.uzmtverireq.uzmtreqplanm_id= utic.uzmtreqplanm.uzmtreqplanm_id \r\n" + 
				"and utic.uzmtrequisito.uzmtrequisito_id= utic.uzmtreqplanm.uzmtrequisito_id \r\n" + 
				"and peaempl_pidm=" +id+ " and utic.uzmtverireq.uzmtreqplanm_id = 1 and rownum < 2 order by uzmtverireq_id";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	
	@GetMapping("/GetRequisito3/{id}")
	public List<ReqplanmVo> requisitod(@PathVariable Long id) throws SQLException{
		String q = "select  DISTINCT(uzmtrequisito_detalle) as nombre, uzmtverireq_estado, uzmtverireq_id \r\n" + 
				"from utic.uzmtverireq,utic.uzmtrequisito,utic.uzmtreqplanm where utic.uzmtverireq.uzmtreqplanm_id= utic.uzmtreqplanm.uzmtreqplanm_id \r\n" + 
				"and utic.uzmtrequisito.uzmtrequisito_id= utic.uzmtreqplanm.uzmtrequisito_id \r\n" + 
				"and peaempl_pidm=" +id+ " and utic.uzmtverireq.uzmtreqplanm_id = 3 and rownum < 2 order by uzmtverireq_id";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	
	
	@GetMapping("/GetRequisito4/{id}")
	public List<ReqplanmVo> requisitoe(@PathVariable Long id) throws SQLException{
		String q = "\r\n" + 
				"SELECT * FROM\r\n" + 
				"(select pprccmt.pprccmt_pidm, pprccmt.pprccmt_cmty_code, pprccmt.pprccmt_text, pprccmt.pprccmt_activity_date,\r\n" + 
				"ptvcmty.ptvcmty_desc, ptvcmty.ptvcmty_activity_date  from PPRCCMT CROSS JOIN PTVCMTY where PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code \r\n" + 
				"AND PPRCCMT_CMTY_CODE IN ('D04','S04') AND PPRCCMT.PPRCCMT_PIDM = "+ id +" AND ROWNUM =1),\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D04','S04') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +")\r\n" + 
				"\r\n" + 
				"UNION \r\n" + 
				"\r\n" + 
				"SELECT * FROM\r\n" + 
				"(select pprccmt.pprccmt_pidm, pprccmt.pprccmt_cmty_code, pprccmt.pprccmt_text, pprccmt.pprccmt_activity_date,\r\n" + 
				"ptvcmty.ptvcmty_desc, ptvcmty.ptvcmty_activity_date  from PPRCCMT CROSS JOIN PTVCMTY where PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code \r\n" + 
				"AND PPRCCMT_CMTY_CODE IN ('D05','S05') AND PPRCCMT.PPRCCMT_PIDM = "+ id +" AND ROWNUM =1),\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D05','S05') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +")\r\n" + 
				"\r\n" + 
				"UNION \r\n" + 
				"\r\n" + 
				"SELECT * FROM\r\n" + 
				"(select pprccmt.pprccmt_pidm, pprccmt.pprccmt_cmty_code, pprccmt.pprccmt_text, pprccmt.pprccmt_activity_date,\r\n" + 
				"ptvcmty.ptvcmty_desc, ptvcmty.ptvcmty_activity_date  from PPRCCMT CROSS JOIN PTVCMTY where PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code \r\n" + 
				"AND PPRCCMT_CMTY_CODE IN ('D06','S06') AND PPRCCMT.PPRCCMT_PIDM = "+ id +" AND ROWNUM =1),\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D06','S06') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +")\r\n" + 
				"\r\n" + 
				"UNION \r\n" + 
				"\r\n" + 
				"SELECT * FROM\r\n" + 
				"(select pprccmt.pprccmt_pidm, pprccmt.pprccmt_cmty_code, pprccmt.pprccmt_text, pprccmt.pprccmt_activity_date,\r\n" + 
				"ptvcmty.ptvcmty_desc, ptvcmty.ptvcmty_activity_date  from PPRCCMT CROSS JOIN PTVCMTY where PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code \r\n" + 
				"AND PPRCCMT_CMTY_CODE IN ('D08') AND PPRCCMT.PPRCCMT_PIDM = "+ id +" AND ROWNUM =1),\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D08') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +")\r\n" + 
				"\r\n" + 
				"UNION \r\n" + 
				"\r\n" + 
				"SELECT * FROM\r\n" + 
				"(select pprccmt.pprccmt_pidm, pprccmt.pprccmt_cmty_code, pprccmt.pprccmt_text, pprccmt.pprccmt_activity_date,\r\n" + 
				"ptvcmty.ptvcmty_desc, ptvcmty.ptvcmty_activity_date  from PPRCCMT CROSS JOIN PTVCMTY where PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code \r\n" + 
				"AND PPRCCMT_CMTY_CODE IN ('D09') AND PPRCCMT.PPRCCMT_PIDM = "+ id +" AND ROWNUM =1),\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D09') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +")\r\n" + 
				"\r\n" + 
				"UNION \r\n" + 
				"\r\n" + 
				"SELECT * FROM\r\n" + 
				"(select pprccmt.pprccmt_pidm, pprccmt.pprccmt_cmty_code, pprccmt.pprccmt_text, pprccmt.pprccmt_activity_date,\r\n" + 
				"ptvcmty.ptvcmty_desc, ptvcmty.ptvcmty_activity_date  from PPRCCMT CROSS JOIN PTVCMTY where PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code \r\n" + 
				"AND PPRCCMT_CMTY_CODE IN ('D010') AND PPRCCMT.PPRCCMT_PIDM = "+ id +" AND ROWNUM =1),\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D010') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +")\r\n" + 
				"\r\n" + 
				"UNION \r\n" + 
				"\r\n" + 
				"SELECT * FROM\r\n" + 
				"(select pprccmt.pprccmt_pidm, pprccmt.pprccmt_cmty_code, pprccmt.pprccmt_text, pprccmt.pprccmt_activity_date,\r\n" + 
				"ptvcmty.ptvcmty_desc, ptvcmty.ptvcmty_activity_date  from PPRCCMT CROSS JOIN PTVCMTY where PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code \r\n" + 
				"AND PPRCCMT_CMTY_CODE IN ('D011') AND PPRCCMT.PPRCCMT_PIDM = "+ id +" AND ROWNUM =1),\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D011') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +")\r\n" + 
				"\r\n" + 
				"UNION \r\n" + 
				"\r\n" + 
				"SELECT * FROM\r\n" + 
				"(select pprccmt.pprccmt_pidm, pprccmt.pprccmt_cmty_code, pprccmt.pprccmt_text, pprccmt.pprccmt_activity_date,\r\n" + 
				"ptvcmty.ptvcmty_desc, ptvcmty.ptvcmty_activity_date  from PPRCCMT CROSS JOIN PTVCMTY where PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code \r\n" + 
				"AND PPRCCMT_CMTY_CODE IN ('D012') AND PPRCCMT.PPRCCMT_PIDM = "+ id +" AND ROWNUM =1),\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D012') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +")";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	

	@GetMapping("/Sanciones/{id}")
	public List<Sanciones> Sanciones(@PathVariable Long id) throws SQLException{
		String q ="select * from PPRCCMT where pprccmt_pidm = " +id+ "";
	System.out.println(q);
	return (List<Sanciones>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(Sanciones.class));
	}
	
	@GetMapping("/Requisito4/{id}")
	public List<ReqplanmVo> requisitof(@PathVariable Long id) throws SQLException{
		String q = "\r\n" + 
				"INSERT WHEN \r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D04','S04') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 2 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D05','S05') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D06','S06') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D08') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D09') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D010') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D011') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1 OR\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D012') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") >= 1\r\n" + 
				"THEN\r\n" + 
				"into utic.uzmtverireq ( uzmtreqplanm_id, PEAEMPL_PIDM, utic.uzmtverireq.uzmtverireq_estado) VALUES (4, "+ id +", 0)\r\n" + 
				" WHEN \r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D04','S04') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D05','S05') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D06','S06') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D08') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2  AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D09') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D010') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D011') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2 AND\r\n" + 
				"(select COUNT (*) AS REINCIDENCIAS from PPRCCMT CROSS JOIN PTVCMTY where\r\n" + 
				"PTVCMTY.PTVCMTY_CODE = pprccmt.pprccmt_cmty_code AND PPRCCMT_CMTY_CODE IN ('D012') AND  PPRCCMT.PPRCCMT_PIDM = "+ id +") < 2\r\n" + 
				"THEN\r\n" + 
				"into utic.uzmtverireq ( uzmtreqplanm_id, PEAEMPL_PIDM, utic.uzmtverireq.uzmtverireq_estado) VALUES (4, "+ id +", 1)\r\n" + 
				"SELECT * FROM DUAL";
	System.out.println(q);
		return (List<ReqplanmVo>) jdbcTemplate.query(q, new BeanPropertyRowMapper<>(ReqplanmVo.class));
	}
	
}
