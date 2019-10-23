package com.espe.crud.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Detalles {

	private String TOTAL;
	
	private Date FECHA_INICIO;
	
	private String CATEGORIA_ESCALAFON;

	public String getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(String tOTAL) {
		TOTAL = tOTAL;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	public Date getFECHA_INICIO() {
		return FECHA_INICIO;
	}

	public void setFECHA_INICIO(Date fECHA_INICIO) {
		FECHA_INICIO = fECHA_INICIO;
	}

	public String getCATEGORIA_ESCALAFON() {
		return CATEGORIA_ESCALAFON;
	}

	public void setCATEGORIA_ESCALAFON(String cATEGORIA_ESCALAFON) {
		CATEGORIA_ESCALAFON = cATEGORIA_ESCALAFON;
	}

	
}
