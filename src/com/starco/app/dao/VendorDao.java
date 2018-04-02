package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.Vendor;

public interface VendorDao {

	List<Vendor> getVendor() throws Exception;

	void addVendor(Vendor vendor) throws Exception;

	void deleteVendor(Vendor vendor);

}
