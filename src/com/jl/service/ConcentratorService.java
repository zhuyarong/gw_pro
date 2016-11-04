package com.jl.service;

import java.util.List;

import com.jl.info.json.RXPKT;
import com.jl.mybean.Concentrator;

public interface ConcentratorService {
	public int save(Concentrator conc);
	public Concentrator getByMac(String mac);
	int save(Concentrator conc, List<RXPKT> rxpkts);
}
