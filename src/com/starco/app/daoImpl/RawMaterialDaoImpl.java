package com.starco.app.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.RawMaterialDao;
import com.starco.app.model.RawMaterials;

@Repository
public class RawMaterialDaoImpl implements RawMaterialDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RawMaterials> listOfRawMaterials() {
		Query query =  sessionFactory.getCurrentSession().createQuery("from RawMaterials");
		List<RawMaterials> rawMaterialsList = (List<RawMaterials>)query.list();
		return rawMaterialsList;
	}

	@Override
	public void addRawMaterial(RawMaterials rawMaterials) {
		sessionFactory.getCurrentSession().save(rawMaterials);
		
	}
}
