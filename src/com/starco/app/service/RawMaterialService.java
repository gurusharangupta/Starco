package com.starco.app.service;

import java.util.List;

import com.starco.app.model.RawMaterials;

public interface RawMaterialService {

	List<RawMaterials> listRawMaterials();

	void addRawMaterial(RawMaterials rawMaterials);

}
