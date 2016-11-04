package com.jl.info.json;

public class RXPKT {
	private String MoteID;
	private String Lati;
	private String Long;
	private String Datr;
	private String steps;
	private int efenceId;
	

	
	public int getEfenceId() {
		return efenceId;
	}
	public void setEfenceId(int efenceId) {
		this.efenceId = efenceId;
	}
	public String getMoteID() {
		return MoteID;
	}
	public void setMoteID(String moteID) {
		MoteID = moteID;
	}
	public String getLati() {
		return Lati;
	}
	public void setLati(String lati) {
		Lati = lati;
	}
	public String getLong() {
		return Long;
	}
	public void setLong(String l) {
		Long = l;
	}
	public String getDatr() {
		return Datr;
	}
	public void setDatr(String datr) {
		Datr = datr;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	

}
