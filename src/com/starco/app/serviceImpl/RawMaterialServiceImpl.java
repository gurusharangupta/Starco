package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.RawMaterialDao;
import com.starco.app.model.RawMaterials;
import com.starco.app.service.RawMaterialService;

@Service
@Transactional
public class RawMaterialServiceImpl implements RawMaterialService{

	@Autowired
	private RawMaterialDao rawMaterialDao;
	
	
	@Override
	public List<RawMaterials> listRawMaterials() {
		return rawMaterialDao.listOfRawMaterials();
	}

	@Override
	public void addRawMaterial(RawMaterials rawMaterials) {
		rawMaterialDao.addRawMaterial(rawMaterials);
		
	}

}
