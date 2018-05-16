package com.starco.app.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import  static com.starco.app.contant.Constants.*;

import com.starco.app.exception.StarcoException;
import com.starco.app.model.Client;
import com.starco.app.model.ClientProductStarco;
import com.starco.app.model.ManufacturedProduct;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.Sales;
import com.starco.app.service.SalesService;

@ManagedBean(name = "salesController")
@ViewScoped
@Component
public class SalesController {

	private Sales sales = new Sales();

	@Autowired
	private ClientController clientController;
	
	@Autowired
	private SalesService salesService;
	
	private List<Sales> salesList = new ArrayList<Sales>();

	private Date date = new Date();
	
	@Autowired
	private RawMaterialController rawMaterialController;
	

	@PostConstruct
	public void init() {
		fetchSalesForToday();
		clientController.fetchClients();
		
		

	}
	
	public void fetchSalesForToday(){
		try {
			salesList = salesService.fetchSalesForToday();
		} catch (Exception e) {
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							"Error occured while fetching sales list"));
			e.printStackTrace();
		}
		
	}
	
	public void fetchSalesForMonth(){
		try {
			salesList = salesService.fetchSalesForMonth();
		} catch (Exception e) {
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							"Error occured while fetching sales list"));
			e.printStackTrace();
		}
		
	}
	
	public void fetchSalesForQuater(){
		try {
			salesList = salesService.fetchSalesForQuater();
		} catch (Exception e) {
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							"Error occured while fetching sales list"));
			e.printStackTrace();
		}
		
	}
	
	public void fetchSalesForYear(){
		try {
			salesList = salesService.fetchSalesForYear();
		} catch (Exception e) {
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							"Error occured while fetching sales list"));
			e.printStackTrace();
		}
		
	}

	public void updateSales() {

		if (clientController.getClientList().size() != 0 && sales.getClient() == null) {
			sales.setClient(clientController.getClientList().get(0));
			sales.setClientProductStarco(new ClientProductStarco());
			
		}else{
		sales.setClient(new Client());
		sales.setClientProductStarco(new ClientProductStarco());
	}
	}

	public Object getClientProductStarco(Integer id) {

		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		Client client = sales.getClient();
		for (ClientProductStarco clientProductStarco : client
				.getClientProductList()) {
			if (id.equals(clientProductStarco.getId())) {
				return clientProductStarco;
			}
		}
		return null;
	}
	
	public void changeClient(){
		System.out.println(sales.getClient().getName());
		
		
	}

	public void calculateSelectedProductDetails() {
		
		float cGST = 0,sGST = 0,iGST = 0,totalGST,totalAmount,carboys,totalCost=0;
		if (sales.getQuantity() != 0
				&& sales.getClientProductStarco()!= null && sales.getClientProductStarco().getPrice() != 0) {
			carboys = sales.getQuantity()
					/ sales.getClientProductStarco().getPacking().getCapacity();
			totalAmount= sales.getQuantity()
					* sales.getClientProductStarco().getPrice();
			sales.setCarboys(carboys);
			sales.setTotalAmount(totalAmount);
			
			
			if(sales.getClient().isOgs()){
				
				iGST = (float) (IGSTPercentage * totalAmount);
				totalGST = sGST + iGST;
				
			}else{
				sGST = (float) (SGSTPercentage * totalAmount);
				cGST = (float) (CGSTPercentage * totalAmount);
				totalGST = sGST + cGST;
				
				
			}
			totalCost = totalGST + totalAmount;
			sales.setTotalCost(totalCost);
			sales.setsGST(sGST);
			sales.setcGST(cGST);
			sales.setiGST(iGST);
			sales.setTotalGST(totalGST);
			
			
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error",
									"Value of Selected Product Price and quantity should be more than zero"));
		}

	}
	
	public void addSales(){
		try {
			float packingQuantity=0,leftProductQuantity=0;
			packingQuantity = sales.getClientProductStarco().getPacking().getQuantity()-sales.getCarboys();
			
			leftProductQuantity = sales.getClientProductStarco().getProduct().getTotalQuantity() - sales.getQuantity();
			
			if(leftProductQuantity<0){
				throw (new StarcoException(
						"Selected Product doest not have the desired quantity left"));
				
				
			}
			
			if(sales.getQuantity()<1){
				throw (new StarcoException(
						"Value of Selected Product Price and quantity should be more than zero"));
				
				
			}
			if(packingQuantity<0){
				throw (new StarcoException(
						"Packing "+ sales.getClientProductStarco().getPacking().getName() +" doesnt have enough quantity left for desired carboys"));
				
				
			}
			Product updatedProduct = sales.getClientProductStarco().getProduct();
			updatedProduct.setTotalQuantity(leftProductQuantity);
			Packing updatedPacking = sales.getClientProductStarco().getPacking();
			updatedPacking.setQuantity(packingQuantity);
			calculateSelectedProductDetails();
			Instant instant = date.toInstant();
			LocalDate saleDate = instant.atZone(ZoneId.systemDefault())
					.toLocalDate();
			if (saleDate.isAfter(LocalDate.now())) {
				throw (new StarcoException(
						"Sale Date cannot be set to future Date"));

			}
			sales.setSaleDate(saleDate);
			salesService.addSales(sales,updatedPacking,updatedProduct);
			sales = new Sales();
			updateSales();
			rawMaterialController.updateAll();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"New Sales Added"));
			
			
		} catch (StarcoException e) {
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		}catch (Exception e) {
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error occured while adding Manufactured Product"));
		}
		
	}

	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
	}

	public List<Sales> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<Sales> salesList) {
		this.salesList = salesList;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
