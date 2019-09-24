package com.espe.crud.model;

import java.util.Date;

public class Detalles {

	private long TOTAL;
	
	private Date FECHA_INICIO;
	
	private String CATEGORIA_ESCALAFON;

	public long getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(long tOTAL) {
		TOTAL = tOTAL;
	}

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
