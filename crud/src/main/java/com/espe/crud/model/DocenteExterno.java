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
@Table(name = "UZMTDOCENTEXTER", schema="UTIC")
public class DocenteExterno {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="UZMTDOCENTEXTER_ID ")
	@SequenceGenerator(name="UZMTDOCENTEXTER_ID ", sequenceName="UZMTDOCENTEXTER_ID ")
	@Column(name = "UZMTDOCENTEXTER_ID")
	private Long id;
	
	@Column(name = "UZMTDOCENTEXTER_APELLI")
	private String apellido;
	
	@Column(name = "UZMTDOCENTEXTER_NOM")
	private String nombre;
	
	@Column(name = "UZMTDOCENTEXTER_CARGO")
	private String cargo;
	
	@Column(name = "UZMTDOCENTEXTER_IDENTIFICACION")
	private String Identificacion;
	
	@Column(name = "UZMTDOCENTEXTER_TELF")
	private String telefono;
	
	@Column(name = "UZMTDOCENTEXTER_EMAIL")
	private String email;
	
	@Column(name = "UZMTDOCENTEXTER_CARRERA")
	private String carrera;
	
	@Column(name = "UZMTDOCENTEXTER_DEPART")
	private String departamento;
	
	@Column(name = "UZMTDOCENTEXTER_USUARIO_CREA")
	private String usuario_crea;
	
	@Column(name = "UZMTDOCENTEXTER_FECHA_CREA")
	private String fecha_crea;
	
	@Column(name = "UZMTDOCENTEXTER_USUARIO_MOD")
	private Date usuario_mod;
	
	@Column(name = "UZMTDOCENTEXTER_FECHA_MOD")
	private Date fecha_mod;
	
	@Column(name = "UZMTDOCENTEXTER_PIDM")
	private String pidm;

	public DocenteExterno() {
	}

	public DocenteExterno(Long id, String apellido, String nombre, String cargo, String identificacion, String telefono,
			String email, String carrera, String departamento, String usuario_crea, String fecha_crea, Date usuario_mod,
			Date fecha_mod, String pidm) {
		super();
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cargo = cargo;
		Identificacion = identificacion;
		this.telefono = telefono;
		this.email = email;
		this.carrera = carrera;
		this.departamento = departamento;
		this.usuario_crea = usuario_crea;
		this.fecha_crea = fecha_crea;
		this.usuario_mod = usuario_mod;
		this.fecha_mod = fecha_mod;
		this.pidm = pidm;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getIdentificacion() {
		return Identificacion;
	}

	public void setIdentificacion(String identificacion) {
		Identificacion = identificacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getUsuario_crea() {
		return usuario_crea;
	}

	public void setUsuario_crea(String usuario_crea) {
		this.usuario_crea = usuario_crea;
	}

	public String getFecha_crea() {
		return fecha_crea;
	}

	public void setFecha_crea(String fecha_crea) {
		this.fecha_crea = fecha_crea;
	}

	public Date getUsuario_mod() {
		return usuario_mod;
	}

	public void setUsuario_mod(Date usuario_mod) {
		this.usuario_mod = usuario_mod;
	}

	public Date getFecha_mod() {
		return fecha_mod;
	}

	public void setFecha_mod(Date fecha_mod) {
		this.fecha_mod = fecha_mod;
	}

	public String getPidm() {
		return pidm;
	}

	public void setPidm(String pidm) {
		this.pidm = pidm;
	}

	@Override
	public String toString() {
		return "DocenteExterno [id=" + id + ", apellido=" + apellido + ", nombre=" + nombre + ", cargo=" + cargo
				+ ", Identificacion=" + Identificacion + ", telefono=" + telefono + ", email=" + email + ", carrera="
				+ carrera + ", departamento=" + departamento + ", usuario_crea=" + usuario_crea + ", fecha_crea="
				+ fecha_crea + ", usuario_mod=" + usuario_mod + ", fecha_mod=" + fecha_mod + ", pidm=" + pidm + "]";
	}
	
}