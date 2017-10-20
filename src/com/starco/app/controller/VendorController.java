package com.starco.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.Vendor;
import com.starco.app.service.VendorService;

@ManagedBean(name="vendorController")
@ViewScoped
@Component
public class VendorController {
	
	private List<Vendor> vendorList = new ArrayList<>();
	private Vendor vendor = new Vendor();
	
	@PostConstruct
	public void init() {
		showVendors();
	}
	
	
	@Autowired
	private VendorService vendorService;
	
	
	public void showVendors() {
		
		setVendorList(vendorService.getVendors());
		
	}
	
	public void addVendor() {
		
		vendorService.addVendor(vendor);
		vendor = new Vendor();
		
	}


	public List<Vendor> getVendorList() {
		return vendorList;
	}


	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}
