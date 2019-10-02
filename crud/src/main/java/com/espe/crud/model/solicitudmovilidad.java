package com.espe.crud.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "UZMTSOLICTMOV", schema="UTIC")
public class solicitudmovilidad {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="UZMTSOLICTMOV_ID ")
	@SequenceGenerator(name="UZMTSOLICTMOV_ID ", sequenceName="UZMTSOLICTMOV_ID ")

	@Column(name = "UZMTSOLICTMOV_ID")
	private Long id;
	
	@Column(name = "UZMTVERIREQ_ID")
	private Long id_verif_req;
	
	@Column(name = "UZMTSOLICTMOV_ESTADO")
	private Boolean estado ;
	
	@Column(name = "UZMTSOLICTMOV_OBSER")
	private String obser;
	
	@Column(name = "UZMTSOLICTMOV_FECH")
	private Date fecha_solicitud;
	
	@Column(name = "UZMTSOLICTMOV_USUARIO_CREA")
	private String usuario_crea;
	
	@Column(name = "UZMTSOLICTMOV_FECHA_CREA")
	private Date fecha_crea;

	@Column(name = "UZMTSOLICTMOV_USUARIO_MOD")
	private String usuario_mod ;
	
	@Column(name = "UZMTSOLICTMOV_FECHA_MOD")
	private Date fecha_mod;
	
	@Column(name = "UZMTSOLICTMOV_PIDM")
	private String pidm;
	

	
	
	
	public solicitudmovilidad() {
		
	}





	public solicitudmovilidad(Long id, Long id_verif_req, Boolean estado, String obser, Date fecha_solicitud,
			String usuario_crea, Date fecha_crea, String usuario_mod, Date fecha_mod, String pidm) {
		super();
		this.id = id;
		this.id_verif_req = id_verif_req;
		this.estado = estado;
		this.obser = obser;
		this.fecha_solicitud = fecha_solicitud;
		this.usuario_crea = usuario_crea;
		this.fecha_crea = fecha_crea;
		this.usuario_mod = usuario_mod;
		this.fecha_mod = fecha_mod;
		this.pidm = pidm;
	}





	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}





	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}





	/**
	 * @return the id_verif_req
	 */
	public Long getId_verif_req() {
		return id_verif_req;
	}





	/**
	 * @param id_verif_req the id_verif_req to set
	 */
	public void setId_verif_req(Long id_verif_req) {
		this.id_verif_req = id_verif_req;
	}





	/**
	 * @return the estado
	 */
	public Boolean getEstado() {
		return estado;
	}





	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}





	/**
	 * @return the obser
	 */
	public String getObser() {
		return obser;
	}





	/**
	 * @param obser the obser to set
	 */
	public void setObser(String obser) {
		this.obser = obser;
	}





	/**
	 * @return the fecha_solicitud
	 */
	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}





	/**
	 * @param fecha_solicitud the fecha_solicitud to set
	 */
	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}





	/**
	 * @return the usuario_crea
	 */
	public String getUsuario_crea() {
		return usuario_crea;
	}





	/**
	 * @param usuario_crea the usuario_crea to set
	 */
	public void setUsuario_crea(String usuario_crea) {
		this.usuario_crea = usuario_crea;
	}





	/**
	 * @return the fecha_crea
	 */
	public Date getFecha_crea() {
		return fecha_crea;
	}





	/**
	 * @param fecha_crea the fecha_crea to set
	 */
	public void setFecha_crea(Date fecha_crea) {
		this.fecha_crea = fecha_crea;
	}





	/**
	 * @return the usuario_mod
	 */
	public String getUsuario_mod() {
		return usuario_mod;
	}





	/**
	 * @param usuario_mod the usuario_mod to set
	 */
	public void setUsuario_mod(String usuario_mod) {
		this.usuario_mod = usuario_mod;
	}





	/**
	 * @return the fecha_mod
	 */
	public Date getFecha_mod() {
		return fecha_mod;
	}





	/**
	 * @param fecha_mod the fecha_mod to set
	 */
	public void setFecha_mod(Date fecha_mod) {
		this.fecha_mod = fecha_mod;
	}





	/**
	 * @return the pidm
	 */
	public String getPidm() {
		return pidm;
	}





	/**
	 * @param pidm the pidm to set
	 */
	public void setPidm(String pidm) {
		this.pidm = pidm;
	}





	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "solicitudmovilidad [id=" + id + ", id_verif_req=" + id_verif_req + ", estado=" + estado + ", obser="
				+ obser + ", fecha_solicitud=" + fecha_solicitud + ", usuario_crea=" + usuario_crea + ", fecha_crea="
				+ fecha_crea + ", usuario_mod=" + usuario_mod + ", fecha_mod=" + fecha_mod + ", pidm=" + pidm + "]";
	}
	
	
	
}
