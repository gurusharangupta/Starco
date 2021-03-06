package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.ClientDao;
import com.starco.app.dao.PackingDao;
import com.starco.app.model.Client;
import com.starco.app.model.Packing;
import com.starco.app.service.ClientService;
import com.starco.app.service.PackingService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	private ClientDao clientDao;

	@Override
	public void addClient(Client client) throws Exception {
		clientDao.addClient(client);
		
	}

	@Override
	public List<Client> clientList() throws Exception {
		return clientDao.clientList();
	}
	
	

}
