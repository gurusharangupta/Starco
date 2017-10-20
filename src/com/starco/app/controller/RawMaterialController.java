package com.starco.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.RawMaterials;
import com.starco.app.service.RawMaterialService;

@ManagedBean(name = "rawMaterialController")
@ViewScoped
@Component
public class RawMaterialController {

	private RawMaterials rawMaterials = new RawMaterials();
	
	private List<RawMaterials> listRawMaterials = new ArrayList<>();
	
	@Autowired
	private RawMaterialService rawMaterialService;
	
	
	
	public void showRawMaterials() {
		setListRawMaterials(rawMaterialService.listRawMaterials());

	}

	public void addRawMaterial() {
		rawMaterialService.addRawMaterial(rawMaterials);
		rawMaterials = new RawMaterials();
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
