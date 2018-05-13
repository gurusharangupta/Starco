package com.starco.app.service;

import java.util.List;

import com.starco.app.model.Packing;

public interface PackingService {

	
	void addPacking(Packing packing) throws Exception;

	List<Packing> listTypeOfPacking() throws Exception;
	
	void updatePacking(Packing packing) throws Exception;
	
}
