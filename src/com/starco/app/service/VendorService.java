package com.starco.app.service;

import java.util.List;

import com.starco.app.model.Vendor;

public interface VendorService {

	List<Vendor> getVendors() throws Exception;

	void addVendor(Vendor vendor) throws Exception;

	void deleteVendor(Vendor vendor);

}
