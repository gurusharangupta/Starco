package com.starco.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.ProductRecipe;
import com.starco.app.model.RawMaterials;
import com.starco.app.model.RawMaterialsStarco;
import com.starco.app.model.Vendor;
import com.starco.app.service.RawMaterialService;

@ManagedBean(name = "rawMaterialStarcoController")
@ViewScoped
@Component
public class RawMaterialStarcoController {

	private RawMaterials selectedRawMaterial = new RawMaterials();
	private RawMaterialsStarco rawMaterialStarco = new RawMaterialsStarco();
	private List<RawMaterials> listSearchedRawMaterials = new ArrayList<>();
	private List<RawMaterialsStarco> listRawMaterialsStarco = new ArrayList<>();
	private String rawMaterial;

	@Autowired
	private RawMaterialService rawMaterialService;

	@Autowired
	private VendorController vendorController;

	@Autowired
	private RawMaterialController rawMaterialController;

	@PostConstruct
	public void init() {
		fetchRawMaterialStarco();

	}

	public void fetchRawMaterialStarco() {

		try {
			listRawMaterialsStarco = rawMaterialService
					.fetchRawMaterialStarco();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while fetching raw materials"));
			e.printStackTrace();
		}
	}

	public List<String> completeText(String query) {
		List<String> results = new ArrayList<>();
		try {
			results = rawMaterialService.getUniqueRawMaterialList(query);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while searching raw materials"));
			e.printStackTrace();
		}
		return results;
	}

	public void searchRawMaterial() {
		try {
			rawMaterialStarco = rawMaterialService
					.getCurrentVendorForRawMaterial(rawMaterial);
			selectedRawMaterial = rawMaterialStarco.getRawMaterials();
			listSearchedRawMaterials = rawMaterialService
					.getNamedRawMaterialList(rawMaterial);

			if (listSearchedRawMaterials.size() == 0) {
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

	public void updateCurrentVendor() {
		if (selectedRawMaterial != null) {
			try {
				rawMaterialStarco.setRawMaterials(selectedRawMaterial);
				rawMaterialService.updateCurrentVendorForRawMaterial(rawMaterialStarco);
				rawMaterialStarco = rawMaterialService
						.getCurrentVendorForRawMaterial(rawMaterial);
				rawMaterialController.updateAll();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
								"Updated Current Vendor for Raw Material"));
			} catch (ConstraintViolationException e) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Error",
										"Vendor already in use for given raw Material"));
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"Error occured while Updating raw materials"));
				e.printStackTrace();
			}
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error",
									"Please select a Raw Material from list to update"));
		}

	}

	public RawMaterialsStarco getRawMaterialStarco(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (RawMaterialsStarco rawMaterialsStarco : listRawMaterialsStarco) {
			if (id.equals(rawMaterialsStarco.getId())) {
				return rawMaterialsStarco;
			}
		}
		return null;
	}

	public RawMaterials getRawMaterials() {
		return selectedRawMaterial;
	}

	public void setRawMaterials(RawMaterials rawMaterials) {
		this.selectedRawMaterial = rawMaterials;
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

	public List<RawMaterialsStarco> getListRawMaterialsStarco() {
		return listRawMaterialsStarco;
	}

	public void setListRawMaterialsStarco(
			List<RawMaterialsStarco> listRawMaterialsStarco) {
		this.listRawMaterialsStarco = listRawMaterialsStarco;
	}
}
