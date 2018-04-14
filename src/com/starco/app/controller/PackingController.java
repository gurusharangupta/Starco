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

import com.starco.app.model.Packing;
import com.starco.app.model.ProductRecipe;
import com.starco.app.model.RawMaterials;
import com.starco.app.model.Vendor;
import com.starco.app.service.PackingService;


@ManagedBean(name = "packingController")
@ViewScoped
@Component
public class PackingController {
	
	@Autowired
	private PackingService packingService;
	
	private Packing packing = new Packing();
	
	private List<Packing> packingList= new ArrayList<>();
	
	@PostConstruct
	public void init() {
		showTypeOfPacking();
	}
	
	public void showTypeOfPacking() {
		try {
			setPackingList(packingService.listTypeOfPacking());
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving type of Packing"));
		}
		System.out.println("Listing Raw Materials");
	}

	public void addNewPacking() {

		try {
			float price = packing.getPrice();
			float weight = packing.getWeight();
			if(price!=0 && weight!=0){
			float pricePerKg = price/weight;
					packing.setPricePerKg(pricePerKg);
					packingService.addPacking(packing);
					packing = new Packing();
					System.out.println("Adding New Packing");
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
									"New Packing Added"));
			}else{
				
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"Value of weight and price should not be zero"));
			}
			
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while adding new type of Packing"));
		}
	}

	public Packing getPacking(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Packing packing : packingList) {
			if (id.equals(packing.getId())) {
				return packing;
			}
		}
		return null;
	}
	
	public Packing getPacking() {
		return packing;
	}

	public void setPacking(Packing packing) {
		this.packing = packing;
	}

	public List<Packing> getPackingList() {
		return packingList;
	}

	public void setPackingList(List<Packing> packingList) {
		this.packingList = packingList;
	}
	

}
