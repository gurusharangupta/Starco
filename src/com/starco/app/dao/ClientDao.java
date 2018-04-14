package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.Client;
import com.starco.app.model.Packing;

public interface ClientDao {

	
	void addClient(Client client) throws Exception;

	List<Client> clientList() throws Exception;
}
