package com.espe.crud.model;

import java.util.Date;

public class Sanciones {

	private long PPRCCMT_PIDM;
	
	
	private String PPRCCMT_CMTY_CODE;
	
	private String  PPRCCMT_TEXT;
	
	private Date PPRCCMT_ACTIVITY_DATE;
	
	private String PTVCMTY_DESC;

	public long getPPRCCMT_PIDM() {
		return PPRCCMT_PIDM;
	}

	public void setPPRCCMT_PIDM(long pPRCCMT_PIDM) {
		PPRCCMT_PIDM = pPRCCMT_PIDM;
	}

	public String getPPRCCMT_CMTY_CODE() {
		return PPRCCMT_CMTY_CODE;
	}

	public void setPPRCCMT_CMTY_CODE(String pPRCCMT_CMTY_CODE) {
		PPRCCMT_CMTY_CODE = pPRCCMT_CMTY_CODE;
	}

	public String getPPRCCMT_TEXT() {
		return PPRCCMT_TEXT;
	}

	public void setPPRCCMT_TEXT(String pPRCCMT_TEXT) {
		PPRCCMT_TEXT = pPRCCMT_TEXT;
	}

	public Date getPPRCCMT_ACTIVITY_DATE() {
		return PPRCCMT_ACTIVITY_DATE;
	}

	public void setPPRCCMT_ACTIVITY_DATE(Date pPRCCMT_ACTIVITY_DATE) {
		PPRCCMT_ACTIVITY_DATE = pPRCCMT_ACTIVITY_DATE;
	}

	public String getPTVCMTY_DESC() {
		return PTVCMTY_DESC;
	}

	public void setPTVCMTY_DESC(String pTVCMTY_DESC) {
		PTVCMTY_DESC = pTVCMTY_DESC;
	}
	
}
