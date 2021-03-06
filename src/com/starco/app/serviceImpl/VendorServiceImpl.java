package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.VendorDao;
import com.starco.app.model.Vendor;
import com.starco.app.service.VendorService;

@Service
@Transactional
public class VendorServiceImpl implements VendorService{

	@Autowired
	private VendorDao vendorDao;
	
	
	@Override
	public List<Vendor> getVendors() throws Exception{
		return vendorDao.getVendor();
		
	}


	@Override
	public void addVendor(Vendor vendor) throws Exception{
		vendorDao.addVendor(vendor);
		
	}


	@Override
	public void deleteVendor(Vendor vendor) {
		vendorDao.deleteVendor(vendor);
		
	}

}
