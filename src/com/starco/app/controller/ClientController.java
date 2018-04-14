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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.Client;
import com.starco.app.model.ClientProductStarco;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.ProductRecipe;
import com.starco.app.service.ClientService;
import com.starco.app.service.ProductService;



@ManagedBean(name = "clientController")
@ViewScoped
@Component
public class ClientController {
	
	private Client client = new Client();
	
	private List<ClientProductStarco> clientProductStarcoList = new ArrayList<ClientProductStarco>();
	
	private List<Client> clientList = null;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private SalesController salesController;

	

	@PostConstruct
	public void init() {
		fetchClients();
		ClientProductStarco clientProductStarco = new ClientProductStarco();
		clientProductStarcoList.add(clientProductStarco);

	}

	public void fetchClients() {
		try {
			clientList = clientService.clientList();
			salesController.updateSales();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving clients"));
		}
		System.out.println("Listing Clients");
	}

	public void addClient() {
	
		boolean error=false;
		for(int i=0;i<clientProductStarcoList.size()-1;i++){
			clientProductStarcoList.get(i).setClient(client);
			for(int j=i+1;j<=clientProductStarcoList.size()-1;j++){
				if(clientProductStarcoList.get(i).getProduct().getProductName().equals(clientProductStarcoList.get(j).getProduct().getProductName())){
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
									"Same type of Product is added more than once"));
					error=true;
				}
			}
		}
		clientProductStarcoList.get(clientProductStarcoList.size()-1).setClient(client);
		if(!error){
		client.setClientProductList(clientProductStarcoList);
		try {
			clientService.addClient(client);
			salesController.updateSales();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"New Client Added"));
			clearProductStarcoList();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while adding new Client"));
			e.printStackTrace();
		}
		
		}
		
	}

	public void addToReciepe() {
		clientProductStarcoList.add(new ClientProductStarco());

	}

	public void removeFromReciepe() {
		if (clientProductStarcoList.size() > 1) {
			clientProductStarcoList.remove(clientProductStarcoList.size() - 1);

		}

	}

	public void clearProductStarcoList() {
		
		clientProductStarcoList.clear();
		clientProductStarcoList.add(new ClientProductStarco());
		client =null;
		client = new Client();
		

	}
	
	public Client getClient(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("no id provided");
		}
		for (Client client : clientList) {
			if (id.equals(client.getId())) {
				return client;
			}
		}
		return null;
	}
	

	public List<ClientProductStarco> getClientProductStarcoList() {
		return clientProductStarcoList;
	}

	public void setClientProductStarcoList(
			List<ClientProductStarco> clientProductStarcoList) {
		this.clientProductStarcoList = clientProductStarcoList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

	

}
