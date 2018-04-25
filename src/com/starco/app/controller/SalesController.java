package com.starco.app.controller;

import java.time.LocalDate;
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

import com.starco.app.model.Client;
import com.starco.app.model.ClientProductStarco;
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
		
		float cGST = 0,sGST = 0,iGST = 0,totalGST,totalAmount,carboys;
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
			if(sales.getQuantity()!=0){
			calculateSelectedProductDetails();
			LocalDate date = LocalDate.now();
			
			sales.setSaleDate(date);
			salesService.addSales(sales);
			sales = new Sales();
			updateSales();
			fetchSalesForToday();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"New Sales Added"));
			}else{
				FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error",
								"Value of Selected Product Price and quantity should be more than zero"));
				
			}
			
		} catch (Exception e) {
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error",
							"Error Occured while adding sale"));
			e.printStackTrace();
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


}
