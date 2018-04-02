package com.starco.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.Vendor;
import com.starco.app.service.VendorService;

@ManagedBean(name = "vendorController")
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
	
	@Autowired
	private RawMaterialController rawMaterialController;

	public void showVendors() {
		try {
			setVendorList(vendorService.getVendors());
			
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving vendors"));
		}
		System.out.println("Listing Vendors");
	}

	public void addVendor() {
		try {
			vendorService.addVendor(vendor);
			vendor = new Vendor();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"New Vendor Added"));
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while adding vendors"));
		}

	}
	
	public void deleteVendor() {
		try {
			vendorService.deleteVendor(vendor);
			showVendors();
			rawMaterialController.showRawMaterials();
			vendor = new Vendor();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Seleted Vendor Deleted"));
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while adding vendors"));
		}

	}

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	public Vendor getVendor(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Vendor vendor : vendorList) {
			if (id.equals(vendor.getId())) {
				return vendor;
			}
		}
		return null;
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
