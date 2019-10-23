package com.espe.crud.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "UZMTEXTERNO", schema="UTIC")
public class externo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="UZMTDOCENTEXTER_ID")
	@SequenceGenerator(name="UZMTDOCENTEXTER_ID", sequenceName="UZMTDOCENTEXTER_ID")	
	@Column(name = "UZMTEXTERNO_ID")
	private Long id;
	
	@Column(name = "UZMTCONVENIO_ID")
	private Long id_convenio;
	
	@Column(name = "UZMTDOCENTEXTER_ID")
    private Long docentexter;
	
	@Column(name = "UZMTSOLICTMOV_ID")
    private Long solicitmov;
	
	@Column(name = "UZMTEXTERNO_GRUP_INV")
    private String grup_inv;
	
	@Column(name = "UZMTEXTERNO_LINEA_INV")
    private String linea_inv;
	
	@Column(name = "UZMTEXTERNO_BENEF")
    private String benef;
	
	@Column(name = "UZMTEXTERNO_FECH_INIC")
    private Date fech_inic;
	
	@Column(name = "UZMTEXTERNO_FECH_FIN")
    private Date fech_fin;
	
	@Column(name = "UZMTEXTERNO_OBJ")
    private String obj;
	
	@Column(name = "UZMTEXTERNO_METOD")
    private String metodo;
	
	@Column(name = "UZMTEXTERNO_META")
    private String meta;
	
	@Column(name = "UZMTEXTERNO_ENTREG")
    private String entreg;
	
	@Column(name = "STUSBGI_CODE")
    private Long code;
	
	@Column(name = "PEAEMPL_PIDM")
    private Long pdm;
	
	@Column(name = "UZMTEXTERNO_USUARIO_CREA")
    private String usuario_crea;
	
	@Column(name = "UZMTEXTERNO_FECHA_CREA")
    private Date fecha_crea;
	
	@Column(name = "UZMTEXTERNO_USUARIO_MOD")
    private String usuario_mod;
	
	@Column(name = "UZMYEXTERNO_FECHA_MOD")
    private Date fecha_mod;
	
	@Column(name = "UZMTEXTERNO_PIDM")
	private String pidem;

	public externo() {
		super();
	}

	public externo(Long id, Long id_convenio, Long docentexter, Long solicitmov, String grup_inv, String linea_inv,
			String benef, Date fech_inic, Date fech_fin, String obj, String metodo, String meta, String entreg,
			Long code, Long pdm, String usuario_crea, Date fecha_crea, String usuario_mod, Date fecha_mod,
			String pidem) {
		super();
		this.id = id;
		this.id_convenio = id_convenio;
		this.docentexter = docentexter;
		this.solicitmov = solicitmov;
		this.grup_inv = grup_inv;
		this.linea_inv = linea_inv;
		this.benef = benef;
		this.fech_inic = fech_inic;
		this.fech_fin = fech_fin;
		this.obj = obj;
		this.metodo = metodo;
		this.meta = meta;
		this.entreg = entreg;
		this.code = code;
		this.pdm = pdm;
		this.usuario_crea = usuario_crea;
		this.fecha_crea = fecha_crea;
		this.usuario_mod = usuario_mod;
		this.fecha_mod = fecha_mod;
		this.pidem = pidem;
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
	 * @return the id_convenio
	 */
	public Long getId_convenio() {
		return id_convenio;
	}

	/**
	 * @param id_convenio the id_convenio to set
	 */
	public void setId_convenio(Long id_convenio) {
		this.id_convenio = id_convenio;
	}

	/**
	 * @return the docentexter
	 */
	public Long getDocentexter() {
		return docentexter;
	}

	/**
	 * @param docentexter the docentexter to set
	 */
	public void setDocentexter(Long docentexter) {
		this.docentexter = docentexter;
	}

	/**
	 * @return the solicitmov
	 */
	public Long getSolicitmov() {
		return solicitmov;
	}

	/**
	 * @param solicitmov the solicitmov to set
	 */
	public void setSolicitmov(Long solicitmov) {
		this.solicitmov = solicitmov;
	}

	/**
	 * @return the grup_inv
	 */
	public String getGrup_inv() {
		return grup_inv;
	}

	/**
	 * @param grup_inv the grup_inv to set
	 */
	public void setGrup_inv(String grup_inv) {
		this.grup_inv = grup_inv;
	}

	/**
	 * @return the linea_inv
	 */
	public String getLinea_inv() {
		return linea_inv;
	}

	/**
	 * @param linea_inv the linea_inv to set
	 */
	public void setLinea_inv(String linea_inv) {
		this.linea_inv = linea_inv;
	}

	/**
	 * @return the benef
	 */
	public String getBenef() {
		return benef;
	}

	/**
	 * @param benef the benef to set
	 */
	public void setBenef(String benef) {
		this.benef = benef;
	}

	/**
	 * @return the fech_inic
	 */
	public Date getFech_inic() {
		return fech_inic;
	}

	/**
	 * @param fech_inic the fech_inic to set
	 */
	public void setFech_inic(Date fech_inic) {
		this.fech_inic = fech_inic;
	}

	/**
	 * @return the fech_fin
	 */
	public Date getFech_fin() {
		return fech_fin;
	}

	/**
	 * @param fech_fin the fech_fin to set
	 */
	public void setFech_fin(Date fech_fin) {
		this.fech_fin = fech_fin;
	}

	/**
	 * @return the obj
	 */
	public String getObj() {
		return obj;
	}

	/**
	 * @param obj the obj to set
	 */
	public void setObj(String obj) {
		this.obj = obj;
	}

	/**
	 * @return the metodo
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * @param metodo the metodo to set
	 */
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	/**
	 * @return the meta
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * @return the entreg
	 */
	public String getEntreg() {
		return entreg;
	}

	/**
	 * @param entreg the entreg to set
	 */
	public void setEntreg(String entreg) {
		this.entreg = entreg;
	}

	/**
	 * @return the code
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @return the pdm
	 */
	public Long getPdm() {
		return pdm;
	}

	/**
	 * @param pdm the pdm to set
	 */
	public void setPdm(Long pdm) {
		this.pdm = pdm;
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
	 * @return the pidem
	 */
	public String getPidem() {
		return pidem;
	}

	/**
	 * @param pidem the pidem to set
	 */
	public void setPidem(String pidem) {
		this.pidem = pidem;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "externo [id=" + id + ", id_convenio=" + id_convenio + ", docentexter=" + docentexter + ", solicitmov="
				+ solicitmov + ", grup_inv=" + grup_inv + ", linea_inv=" + linea_inv + ", benef=" + benef
				+ ", fech_inic=" + fech_inic + ", fech_fin=" + fech_fin + ", obj=" + obj + ", metodo=" + metodo
				+ ", meta=" + meta + ", entreg=" + entreg + ", code=" + code + ", pdm=" + pdm + ", usuario_crea="
				+ usuario_crea + ", fecha_crea=" + fecha_crea + ", usuario_mod=" + usuario_mod + ", fecha_mod="
				+ fecha_mod + ", pidem=" + pidem + "]";
	}
	
	
}