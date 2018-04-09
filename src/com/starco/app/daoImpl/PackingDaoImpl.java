package com.starco.app.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.PackingDao;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;


@Repository
public class PackingDaoImpl implements PackingDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addPacking(Packing packing) throws Exception {
		sessionFactory.getCurrentSession().save(packing);
		
	}

	@Override
	public List<Packing> listTypeOfPacking() throws Exception {
		Query query =  sessionFactory.getCurrentSession().createQuery("from Packing");
		List<Packing> packingList = (List<Packing>)query.list();
		return packingList;
	}

	
}
