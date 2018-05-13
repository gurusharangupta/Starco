package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.Packing;

public interface PackingDao {

	
	void addPacking(Packing packing) throws Exception;

	List<Packing> listTypeOfPacking() throws Exception;

	void updatePacking(Packing packing) throws Exception;
}
