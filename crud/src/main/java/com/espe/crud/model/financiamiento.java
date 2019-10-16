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
@Table(name = "UZMTFINAN", schema="UTIC")
public class financiamiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="UZMTFINAN_ID")
	@SequenceGenerator(name="UZMTFINAN_ID", sequenceName="UZMTFINAN_ID")
	@Column(name = "UZMTFINAN_ID")
	private Long ide;
	
	@Column(name = "UZMTMGESTINV_ID")
	private Long id_gestinv;
	
	@Column(name = "UZMTFMOVILIDAD_ID")
	private Long id_fmovilidad;
	
	@Column(name = "UZMTESTINTER_ID")
	private Long id_testint;
	
	@Column(name = "UZMTEXTERNO_ID")
	private Long id_externo;
	
	@Column(name = "UZMTFINAN_PASAJE")
	private Long id_pasaje;
	
	@Column(name = "UZMTFINAN_VIATICO")
	private Long finan_viatico;
	
	@Column(name = "UZMTFINAN_OTROS")
	private Long otros;
	
	@Column(name = "UZMTFINAN_TOTAL")
	private Long total;
	
	@Column(name = "UZMTFINAN_USUARIO_CREA")
	private String usuario_crea;
	
	@Column(name = "UZMTFINAN_FECHA_CREA")
	private Date fecha_crea;
	
	@Column(name = "UZMTFINAN_USUARIO_MOD")
	private String usuario_mod;
	
	@Column(name = "UZMTFINAN_FECHA__MOD")
	private Date fecha_mod;
	
	@Column(name = "UZMTFINAN_AYUDA")
	private Long ayuda;
	
	@Column(name = "UZMTFINAN_PIDM")
	private String pidem;

	public financiamiento(Long id_gestinv, Long id_fmovilidad, Long id_testint, Long id_externo,
			Long id_pasaje, Long finan_viatico, Long otros, Long total, String usuario_crea, Date fecha_crea,
			String usuario_mod, Date fecha_mod, Long ayuda, String pidem) {
		super();
		
		this.id_gestinv = id_gestinv;
		this.id_fmovilidad = id_fmovilidad;
		this.id_testint = id_testint;
		this.id_externo = id_externo;
		this.id_pasaje = id_pasaje;
		this.finan_viatico = finan_viatico;
		this.otros = otros;
		this.total = total;
		this.usuario_crea = usuario_crea;
		this.fecha_crea = fecha_crea;
		this.usuario_mod = usuario_mod;
		this.fecha_mod = fecha_mod;
		this.ayuda = ayuda;
		this.pidem = pidem;
	}

	public financiamiento() {
		super();
	}

	/**
	 * @return the ide
	 */
	public Long getIde() {
		return ide;
	}

	/**
	 * @param ide the ide to set
	 */
	public void setIde(Long ide) {
		this.ide = ide;
	}

	/**
	 * @return the id_gestinv
	 */
	public Long getId_gestinv() {
		return id_gestinv;
	}

	/**
	 * @param id_gestinv the id_gestinv to set
	 */
	public void setId_gestinv(Long id_gestinv) {
		this.id_gestinv = id_gestinv;
	}

	/**
	 * @return the id_fmovilidad
	 */
	public Long getId_fmovilidad() {
		return id_fmovilidad;
	}

	/**
	 * @param id_fmovilidad the id_fmovilidad to set
	 */
	public void setId_fmovilidad(Long id_fmovilidad) {
		this.id_fmovilidad = id_fmovilidad;
	}

	/**
	 * @return the id_testint
	 */
	public Long getId_testint() {
		return id_testint;
	}

	/**
	 * @param id_testint the id_testint to set
	 */
	public void setId_testint(Long id_testint) {
		this.id_testint = id_testint;
	}

	/**
	 * @return the id_externo
	 */
	public Long getId_externo() {
		return id_externo;
	}

	/**
	 * @param id_externo the id_externo to set
	 */
	public void setId_externo(Long id_externo) {
		this.id_externo = id_externo;
	}

	/**
	 * @return the id_pasaje
	 */
	public Long getId_pasaje() {
		return id_pasaje;
	}

	/**
	 * @param id_pasaje the id_pasaje to set
	 */
	public void setId_pasaje(Long id_pasaje) {
		this.id_pasaje = id_pasaje;
	}

	/**
	 * @return the finan_viatico
	 */
	public Long getFinan_viatico() {
		return finan_viatico;
	}

	/**
	 * @param finan_viatico the finan_viatico to set
	 */
	public void setFinan_viatico(Long finan_viatico) {
		this.finan_viatico = finan_viatico;
	}

	/**
	 * @return the otros
	 */
	public Long getOtros() {
		return otros;
	}

	/**
	 * @param otros the otros to set
	 */
	public void setOtros(Long otros) {
		this.otros = otros;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
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
	 * @return the ayuda
	 */
	public Long getAyuda() {
		return ayuda;
	}

	/**
	 * @param ayuda the ayuda to set
	 */
	public void setAyuda(Long ayuda) {
		this.ayuda = ayuda;
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
		return "financiamiento [ide=" + ide + ", id_gestinv=" + id_gestinv + ", id_fmovilidad=" + id_fmovilidad
				+ ", id_testint=" + id_testint + ", id_externo=" + id_externo + ", id_pasaje=" + id_pasaje
				+ ", finan_viatico=" + finan_viatico + ", otros=" + otros + ", total=" + total + ", usuario_crea="
				+ usuario_crea + ", fecha_crea=" + fecha_crea + ", usuario_mod=" + usuario_mod + ", fecha_mod="
				+ fecha_mod + ", ayuda=" + ayuda + ", pidem=" + pidem + "]";
	}
	
	

	
	
	
	
	
	
}