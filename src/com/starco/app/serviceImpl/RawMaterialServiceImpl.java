package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.RawMaterialDao;
import com.starco.app.model.RawMaterials;
import com.starco.app.model.RawMaterialsStarco;
import com.starco.app.service.RawMaterialService;

@Service
@Transactional
public class RawMaterialServiceImpl implements RawMaterialService {

	@Autowired
	private RawMaterialDao rawMaterialDao;

	@Override
	public List<RawMaterials> listRawMaterials() throws Exception {
		return rawMaterialDao.listOfRawMaterials();
	}

	@Override
	public void addRawMaterial(RawMaterials rawMaterials) throws Exception {
		rawMaterialDao.addRawMaterial(rawMaterials);

	}

	@Override
	public List<String> getUniqueRawMaterialList(String query) throws Exception {
		return rawMaterialDao.getUniqueRawMaterialList(query);
	}

	@Override
	public List<RawMaterials> getNamedRawMaterialList(String rawMaterial)
			throws Exception {

		return rawMaterialDao.getNamedRawMaterialList(rawMaterial);
	}

	@Override
	public void updateCurrentVendorForRawMaterial(RawMaterials rawMaterials,int moq)
			throws ConstraintViolationException,Exception {
		RawMaterialsStarco rawMaterialsStarco = new  RawMaterialsStarco();
		rawMaterialsStarco.setName(rawMaterials.getName());
		rawMaterialsStarco.setPrice(rawMaterials.getPrice());
		rawMaterialsStarco.setRawMaterials(rawMaterials);
		rawMaterialsStarco.setMoq(moq);
		
		rawMaterialDao.updateCurrentVendorForRawMaterial(rawMaterialsStarco);

	}

	@Override
	public RawMaterialsStarco getCurrentVendorForRawMaterial(String rawMaterial) throws Exception {
		return rawMaterialDao.getCurrentVendorForRawMaterial(rawMaterial);
		
	}

}
