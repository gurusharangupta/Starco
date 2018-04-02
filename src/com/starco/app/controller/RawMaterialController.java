package com.starco.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.RawMaterials;
import com.starco.app.service.RawMaterialService;

 
@ManagedBean(name = "rawMaterialController")
@ViewScoped
@Component
public class RawMaterialController {

	@PostConstruct
	public void init() {
		showRawMaterials();
	}

	private RawMaterials rawMaterials = new RawMaterials();

	private List<RawMaterials> listRawMaterials = new ArrayList<>();

	@Autowired
	private RawMaterialService rawMaterialService;

	public void showRawMaterials() {
		try {
			setListRawMaterials(rawMaterialService.listRawMaterials());
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving raw materials"));
		}
		System.out.println("Listing Raw Materials");
	}

	public void addRawMaterial() {

		try {
			rawMaterialService.addRawMaterial(rawMaterials);
			rawMaterials = new RawMaterials();
			System.out.println("Adding Raw Materials");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Raw Materials Added"));
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while add raw materials"));
		}
	}

	public List<RawMaterials> getListRawMaterials() {
		return listRawMaterials;
	}

	public void setListRawMaterials(List<RawMaterials> listRawMaterials) {
		this.listRawMaterials = listRawMaterials;
	}

	public RawMaterials getRawMaterials() {
		return rawMaterials;
	}

	public void setRawMaterials(RawMaterials rawMaterials) {
		this.rawMaterials = rawMaterials;
	}
}
