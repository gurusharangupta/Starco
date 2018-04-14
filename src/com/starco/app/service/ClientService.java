package com.starco.app.service;

import java.util.List;

import com.starco.app.model.Client;
import com.starco.app.model.Packing;

public interface ClientService {

	
	void addClient(Client client) throws Exception;

	List<Client> clientList() throws Exception;
	
}
