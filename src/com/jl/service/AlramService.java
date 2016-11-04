package com.jl.service;

import java.util.List;

import com.jl.info.json.RXPKT;

public interface AlramService {
	public boolean Alram(List<RXPKT> alramRxpkt, int userId);

}
