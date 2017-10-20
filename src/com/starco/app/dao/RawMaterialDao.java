package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.RawMaterials;

public interface RawMaterialDao {

	List<RawMaterials> listOfRawMaterials();

	void addRawMaterial(RawMaterials rawMaterials);

}
