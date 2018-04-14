package com.starco.app.dao;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.starco.app.model.RawMaterials;
import com.starco.app.model.RawMaterialsStarco;

public interface RawMaterialDao {

	List<RawMaterials> listOfRawMaterials() throws Exception;

	void addRawMaterial(RawMaterials rawMaterials) throws Exception;

	List<String> getUniqueRawMaterialList(String query) throws Exception;

	List<RawMaterials> getNamedRawMaterialList(String rawMaterial) throws Exception;

	void updateCurrentVendorForRawMaterial(RawMaterialsStarco rawMaterialsStarco) throws ConstraintViolationException, Exception;

	RawMaterialsStarco getCurrentVendorForRawMaterial(String rawMaterial) throws Exception;

	List<RawMaterialsStarco> fetchRawMaterialStarco()throws Exception;

	void updateRawMaterial(RawMaterials rawMaterials)throws ConstraintViolationException,Exception;

}
