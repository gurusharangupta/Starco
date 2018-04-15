package com.starco.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.ProductRecipe;
import com.starco.app.model.RawMaterials;
import com.starco.app.service.ProductService;

@ManagedBean(name = "productController")
@ViewScoped
@Component
public class ProductController {

	@Autowired
	private ProductService productService;

	private List<Product> listFinishedGoods = new ArrayList<>();

	private Product product = new Product();

	private List<ProductRecipe> listProductReciepe = new ArrayList<>();

	@PostConstruct
	public void init() {
		showfinishedGoods();
		ProductRecipe productRecipe = new ProductRecipe();
		listProductReciepe.add(productRecipe);

	}

	public void showfinishedGoods() {
		try {
			listFinishedGoods = productService.listFinishedGoodsProduct();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving finished goods"));
		}
		System.out.println("Listing Finished Goods");
	}

	public void updateFinishedProduct(Product product) throws Exception{
		
			productService.updateProduct(product);
		
	}
	
	
	public void addFinishedProduct() {
		calculateAllCost();
		boolean error=false;
		for(int i=0;i<listProductReciepe.size()-1;i++){
			listProductReciepe.get(i).setProduct(product);
			for(int j=i+1;j<=listProductReciepe.size()-1;j++){
				if(listProductReciepe.get(i).getRawMaterialsStarco().getRawMaterials().getName().equals(listProductReciepe.get(j).getRawMaterialsStarco().getRawMaterials().getName())){
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
									"Same type of Raw Material is mentioned more than once"));
					error=true;
				}
			}
		}
		listProductReciepe.get(listProductReciepe.size()-1).setProduct(product);
		if(!error){
			Set<ProductRecipe> productReciepeSet = new HashSet<ProductRecipe>(listProductReciepe);
		product.setProductRecipe(productReciepeSet);
		try {
			productService.addFinishedProduct(product);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Finished Product Added"));
			clearReciepeList();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while adding finished goods"));
			e.printStackTrace();
		}
		
		}
		
	}

	public void addToReciepe() {
		calculateAllCost();
		listProductReciepe.add(new ProductRecipe());

	}

	public void removeFromReciepe() {
		calculateAllCost();
		if (listProductReciepe.size() > 1) {
			listProductReciepe.remove(listProductReciepe.size() - 1);

		}

	}

	public void clearReciepeList() {
		
			listProductReciepe.clear();
			listProductReciepe.add(new ProductRecipe());
			product =null;
			product = new Product();
		

	}
	
	public void calculateAllCost(){
		float reciepeCost=0,totalQuantity = 0;
		for(ProductRecipe productRecipe: listProductReciepe){
			reciepeCost+= productRecipe.getRawMaterialsStarco().getRawMaterials()
					.getPrice() * productRecipe.getQuantity();
			totalQuantity+=productRecipe.getQuantity();
		}
		
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
		
		
	}

	public Product getProduct(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Product product : listFinishedGoods) {
			if (id.equals(product.getId())) {
				return product;
			}
		}
		return null;
	}
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ProductRecipe> getListProductReciepe() {
		return listProductReciepe;
	}

	public void setListProductReciepe(List<ProductRecipe> listProductReciepe) {
		this.listProductReciepe = listProductReciepe;
	}

	public List<Product> getListFinishedGoods() {
		return listFinishedGoods;
	}

	public void setListFinishedGoods(List<Product> listFinishedGoods) {
		this.listFinishedGoods = listFinishedGoods;
	}

}
