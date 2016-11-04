package com.jl.info;

import java.io.Serializable;

public class PUSH_DATA implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6531194298540974984L;
	private String protocolVersion;
	private String randomToken;
	private String identifier;
	private String MAC_address;
	private String UTC_time;
	private String json;
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	public String getRandomToken() {
		return randomToken;
	}
	public void setRandomToken(String randomToken) {
		this.randomToken = randomToken;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getMAC_address() {
		return MAC_address;
	}
	public void setMAC_address(String mAC_address) {
		MAC_address = mAC_address;
	}
	public String getUTC_time() {
		return UTC_time;
	}
	public void setUTC_time(String uTC_time) {
		UTC_time = uTC_time;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
}
