package com.starco.app.controller;

import static com.starco.app.contant.Constants.commissionPriceForCustomer;
import static com.starco.app.contant.Constants.commissionPriceForDealer;
import static com.starco.app.contant.Constants.commissionPriceForExporter;
import static com.starco.app.contant.Constants.commissionPriceInCash;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

import com.starco.app.exception.StarcoException;
import com.starco.app.model.ManufacturedProduct;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.ProductRecipe;
import com.starco.app.model.RawMaterials;
import com.starco.app.model.RawMaterialsStarco;
import com.starco.app.service.ProductService;

@ManagedBean(name = "productController")
@ViewScoped
@Component
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private RawMaterialController rawMaterialController;

	private List<Product> listFinishedGoods = new ArrayList<>();

	private Product product = new Product();

	private List<ProductRecipe> listProductReciepe = new ArrayList<>();

	private ManufacturedProduct manufacturedProduct = new ManufacturedProduct();

	private List<ManufacturedProduct> manufacturedProductList = new ArrayList<ManufacturedProduct>();

	private Date date = new Date();

	@PostConstruct
	public void init() {
		showfinishedGoods();
		fetchManufacturedProductForToday();
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

	public void updateFinishedProduct(Product product) throws Exception {

		productService.updateProduct(product);

	}

	public void fetchManufacturedProductForToday() {
		try {
			manufacturedProductList = productService.fetchManufacturedProductForToday();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving finished goods"));
		}
		System.out.println("Listing Finished Goods");
	}
	
	public void fetchManufacturedProductForMonth() {
		try {
			manufacturedProductList = productService.fetchManufacturedProductForMonth();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving finished goods"));
		}
		System.out.println("Listing Finished Goods");
	}
	
	public void fetchManufacturedProductForQuater() {
		try {
			manufacturedProductList = productService.fetchManufacturedProductForQuater();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving finished goods"));
		}
		System.out.println("Listing Finished Goods");
	}
	
	public void fetchManufacturedProductForYear() {
		try {
			manufacturedProductList = productService.fetchManufacturedProductForYear();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving finished goods"));
		}
		System.out.println("Listing Finished Goods");
	}

	public void addManufacturedProduct() {
		float rawMaterialpercentage = 0, rawMaterialInProductManufactured = 0, currentRawMaterialQuantity = 0;
		float totalQuantity=0;
		String batchNumber;

		try {
			Instant instant = date.toInstant();
			LocalDate manufacturedDate = instant.atZone(ZoneId.systemDefault())
					.toLocalDate();
			if (manufacturedDate.isAfter(LocalDate.now())) {
				throw (new StarcoException(
						"Manufacture Date cannot be set to future Date"));

			}
			batchNumber = Integer.toString(manufacturedDate.getYear())
					+ Integer.toString(manufacturedDate.getMonthValue())
					+ Integer.toString(manufacturedDate.getDayOfMonth()) + "/"
					+ manufacturedProduct.getPlace().charAt(0);
			totalQuantity = manufacturedProduct.getYield() + manufacturedProduct.getProduct().getTotalQuantity();
			manufacturedProduct.setManufactureDate(manufacturedDate);
			manufacturedProduct.setBatchNumber(batchNumber);
			manufacturedProduct.getProduct().setManufacturedProduct(
					manufacturedProduct);
			
			manufacturedProduct.getProduct().setTotalQuantity(totalQuantity);
			Product product = manufacturedProduct.getProduct();
			for (ProductRecipe productRecipe : product.getProductRecipe()) {

				rawMaterialpercentage = (productRecipe.getQuantity() / product
						.getTotalQuantity()) * 100;
				rawMaterialInProductManufactured = (rawMaterialpercentage / 100)
						* manufacturedProduct.getQuantity();
				currentRawMaterialQuantity = productRecipe
						.getRawMaterialsStarco().getCurrentQuantity()
						- rawMaterialInProductManufactured;
				if (currentRawMaterialQuantity < 0) {
					throw (new StarcoException("Raw Material "
							+ productRecipe.getRawMaterialsStarco()
									.getRawMaterials().getName()
							+ " is not enough to manufacture the product "+ manufacturedProduct.getProduct().getProductName()));

				} else {
					productRecipe.getRawMaterialsStarco().setCurrentQuantity(
							currentRawMaterialQuantity);
				}

			}

			productService.addManufacturedProduct(manufacturedProduct);
			rawMaterialController.updateAll();
			manufacturedProduct = new ManufacturedProduct();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Manufactured Product Added"));
		} catch (StarcoException e) {
			manufacturedProduct = new ManufacturedProduct();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		}catch (Exception e) {
			manufacturedProduct = new ManufacturedProduct();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error occured while adding Manufactured Product"));
		}

	}

	public void addFinishedProduct() {
		calculateAllCost();
		boolean error = false;
		if (product.getTotalCost() != 0 && product.getTotalCost() != 0) {
			for (int i = 0; i < listProductReciepe.size() - 1; i++) {
				listProductReciepe.get(i).setProduct(product);
				for (int j = i + 1; j <= listProductReciepe.size() - 1; j++) {
					if (listProductReciepe
							.get(i)
							.getRawMaterialsStarco()
							.getRawMaterials()
							.getName()
							.equals(listProductReciepe.get(j)
									.getRawMaterialsStarco().getRawMaterials()
									.getName())) {
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_ERROR,
												"Error",
												"Same type of Raw Material is mentioned more than once"));
						error = true;
					}
				}
			}
			listProductReciepe.get(listProductReciepe.size() - 1).setProduct(
					product);
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error",
									"Value of Material Cost or Total Cost should be more than zero"));
			error = true;
		}
		if (!error) {
			Set<ProductRecipe> productReciepeSet = new HashSet<ProductRecipe>(
					listProductReciepe);
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
		product = null;
		product = new Product();

	}

	public void calculateAllCost() {
		float reciepeCost = 0, totalQuantity = 0;
		for (ProductRecipe productRecipe : listProductReciepe) {
			reciepeCost += productRecipe.getRawMaterialsStarco()
					.getRawMaterials().getPrice()
					* productRecipe.getQuantity();
			totalQuantity += productRecipe.getQuantity();
		}
		if (totalQuantity != 0) {
			product.setMaterialCost(reciepeCost / totalQuantity);
			float totalCost = product.getMaterialCost()
					+ product.getCostOfEnergyAndLabor()
					+ product.getCostOfPacking();
			float priceForExporter = (float) (totalCost * commissionPriceForExporter);
			float priceForDealer = (float) (totalCost * commissionPriceForDealer);
			float priceForCustomer = (float) (totalCost * commissionPriceForCustomer);
			float priceInCash = (float) (totalCost * commissionPriceInCash);

			product.setTotalCost(totalCost);
			product.setPriceForExporter(priceForExporter);
			product.setPriceForDealer(priceForDealer);
			product.setPriceForCustomer(priceForCustomer);
			product.setPriceInCash(priceInCash);
			product.setTotalQuantity(0);
		}

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

	public ManufacturedProduct getManufacturedProduct() {
		return manufacturedProduct;
	}

	public void setManufacturedProduct(ManufacturedProduct manufacturedProduct) {
		this.manufacturedProduct = manufacturedProduct;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ManufacturedProduct> getManufacturedProductList() {
		return manufacturedProductList;
	}

	public void setManufacturedProductList(
			List<ManufacturedProduct> manufacturedProductList) {
		this.manufacturedProductList = manufacturedProductList;
	}

}
