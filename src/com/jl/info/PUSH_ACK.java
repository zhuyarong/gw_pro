package com.jl.info;

import java.io.Serializable;

public class PUSH_ACK  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4707639769070665288L;
	private String protocolVersion;
	private String randomToken;
	private String identifier;
	private String MAC_address;
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
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
