package com.starco.app.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.VendorDao;
import com.starco.app.model.Vendor;

@Repository
public class VendorDaoImpl implements VendorDao{

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<Vendor> getVendor() {
		Query query =  sessionFactory.getCurrentSession().createQuery("from Vendor");
		List<Vendor> vendorList = (List<Vendor>)query.list();
		return vendorList;
		
	}



	@Override
	public void addVendor(Vendor vendor) {
		sessionFactory.getCurrentSession().save(vendor);
	}

}
