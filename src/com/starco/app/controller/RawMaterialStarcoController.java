package com.starco.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.RawMaterials;
import com.starco.app.model.RawMaterialsStarco;
import com.starco.app.service.RawMaterialService;


@ManagedBean(name = "rawMaterialStarcoController")
@ViewScoped
@Component
public class RawMaterialStarcoController {

	
	private RawMaterials rawMaterials = new RawMaterials();
	private RawMaterialsStarco rawMaterialStarco = new RawMaterialsStarco();
	private List<RawMaterials> listSearchedRawMaterials = new ArrayList<>();
	private String rawMaterial;
	
	@Autowired
	private RawMaterialService rawMaterialService;
	
	@Autowired
	private VendorController vendorController;
	
	@Autowired
	private RawMaterialController rawMaterialController;
	
	
	
	
	public List<String> completeText(String query) {
		List<String> results = new ArrayList<>();
		try{
			results = rawMaterialService.getUniqueRawMaterialList(query);
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while searching raw materials"));
			e.printStackTrace();
		}
        return results;
    }
	
	public void searchRawMaterial(){
		try {
			listSearchedRawMaterials = rawMaterialService.getNamedRawMaterialList(rawMaterial);
			rawMaterialStarco = rawMaterialService.getCurrentVendorForRawMaterial(rawMaterial);
			if(listSearchedRawMaterials.size() == 0){
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
								"No such raw material Found"));
				
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while searching raw materials"));
			e.printStackTrace();
		}
		
	}

	public void updateCurrentVendor(){

		try {
			rawMaterialService.updateCurrentVendorForRawMaterial(rawMaterials,rawMaterialStarco.getMoq());
			rawMaterialStarco = rawMaterialService.getCurrentVendorForRawMaterial(rawMaterial);
			vendorController.showVendors();
			rawMaterialController.showRawMaterials();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Updated Current Vendor for Raw Material"));
		}catch (ConstraintViolationException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Vendor already in use for given raw Material"));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while Updating raw materials"));
			e.printStackTrace();
		}
		
		
	}

	public RawMaterials getRawMaterials() {
		return rawMaterials;
	}


	public void setRawMaterials(RawMaterials rawMaterials) {
		this.rawMaterials = rawMaterials;
	}


	public String getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(String rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public List<RawMaterials> getListSearchedRawMaterials() {
		return listSearchedRawMaterials;
	}

	public void setListSearchedRawMaterials(
			List<RawMaterials> listSearchedRawMaterials) {
		this.listSearchedRawMaterials = listSearchedRawMaterials;
	}

	public RawMaterialsStarco getRawMaterialStarco() {
		return rawMaterialStarco;
	}

	public void setRawMaterialStarco(RawMaterialsStarco rawMaterialStarco) {
		this.rawMaterialStarco = rawMaterialStarco;
	}
}
