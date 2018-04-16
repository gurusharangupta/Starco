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

import com.starco.app.model.Product;
import com.starco.app.model.ProductRecipe;
import com.starco.app.model.RawMaterials;
import com.starco.app.model.Vendor;
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

	private RawMaterials selectedRawmaterial = new RawMaterials();

	private List<RawMaterials> listRawMaterials = new ArrayList<>();
	
	private List<Product> listFinishedGoods = new ArrayList<>();
	
	private float selectedRawMaterialPrice;

	@Autowired
	private RawMaterialService rawMaterialService;
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private RawMaterialStarcoController rawMaterialStarcoController;

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
	
	public void rawMaterialPrice() {
		selectedRawMaterialPrice = selectedRawmaterial.getPrice();
	}
	

	public void editRawMaterial() {

		try {
			rawMaterialService.updateRawMaterial(selectedRawmaterial);
			float reciepeCost = 0,totalQuantity=0;
			boolean productReciepeUpdate=false;
			for(RawMaterials rawMaterial:listRawMaterials){
				if(rawMaterial.equals(selectedRawmaterial)){
					if(selectedRawMaterialPrice!=selectedRawmaterial.getPrice()){
						if(selectedRawmaterial.getRawMaterialsStarco()!=null){						
							listFinishedGoods =	productController.getListFinishedGoods();
							for(Product product:listFinishedGoods){
								
								for(ProductRecipe productReciepe: product.getProductRecipe()){
									if( productReciepe.getRawMaterialsStarco().getRawMaterials().equals( selectedRawmaterial)){
										reciepeCost+= selectedRawmaterial
												.getPrice() * productReciepe.getQuantity();
										totalQuantity+=productReciepe.getQuantity();
										productReciepeUpdate=true;
									}else{
										reciepeCost+= productReciepe.getRawMaterialsStarco().getRawMaterials()
												.getPrice() * productReciepe.getQuantity();	
										totalQuantity+=productReciepe.getQuantity();
									}
								}
								if(productReciepeUpdate){
										product.setMaterialCost(reciepeCost/totalQuantity);
										float totalCost = product.getMaterialCost() + product.getCostOfEnergyAndLabor() + product.getCostOfPacking();
										float priceForExporter = (float) (totalCost * 1.25);
										float priceForDealer = (float) (totalCost * 0.25);
										float priceForCustomer = (float) (totalCost * 0.85);
										float priceInCash = (float) (totalCost * 1.85);
										
										product.setTotalCost(totalCost);
										product.setPriceForExporter(priceForExporter);
										product.setPriceForDealer(priceForDealer);
										product.setPriceForCustomer(priceForCustomer);
										product.setPriceInCash(priceInCash);
										try{
										productController.updateFinishedProduct(product);
										}catch(Exception e){
											FacesContext.getCurrentInstance().addMessage(
													null,
													new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
															"Error occured while updating prices for product using same raw Material"));
										}
									}
								productReciepeUpdate = false;
								
								}
								
							
						}
					}
				}
			}
			showRawMaterials();
			productController.showfinishedGoods();
			rawMaterialStarcoController.fetchRawMaterialStarco();
			System.out.println("Editing Raw Materials");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Raw Materials Edited"));
		} catch (ConstraintViolationException e) {
			showRawMaterials();
			selectedRawmaterial=new RawMaterials();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Raw Material from same vendor is already present"));
		} catch (Exception e) {
			showRawMaterials();
			selectedRawmaterial=new RawMaterials();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while editing raw materials"));
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

	public RawMaterials getSelectedRawmaterial() {
		return selectedRawmaterial;
	}

	public void setSelectedRawmaterial(RawMaterials selectedRawmaterial) {
		this.selectedRawmaterial = selectedRawmaterial;
	}

	public List<Product> getListFinishedGoods() {
		return listFinishedGoods;
	}

	public void setListFinishedGoods(List<Product> listFinishedGoods) {
		this.listFinishedGoods = listFinishedGoods;
	}
}
