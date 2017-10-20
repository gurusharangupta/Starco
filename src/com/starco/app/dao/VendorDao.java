package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.Vendor;

public interface VendorDao {

	List<Vendor> getVendor();

	void addVendor(Vendor vendor);

}
