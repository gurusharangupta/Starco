package com.starco.app.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.starco.app.model.RawMaterials;
import com.starco.app.model.RawMaterialsStarco;

public interface RawMaterialService {

	List<RawMaterials> listRawMaterials() throws Exception;

	void addRawMaterial(RawMaterials rawMaterials) throws Exception;
	
	List<String> getUniqueRawMaterialList(String query) throws Exception;
	
	List<RawMaterials> getNamedRawMaterialList(String rawMaterial) throws Exception;

	void updateCurrentVendorForRawMaterial(RawMaterials rawMaterials, int moq) throws ConstraintViolationException,Exception;

	RawMaterialsStarco getCurrentVendorForRawMaterial(String rawMaterial) throws Exception;

}
