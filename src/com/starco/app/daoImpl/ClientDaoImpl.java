package com.starco.app.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.ClientDao;
import com.starco.app.dao.PackingDao;
import com.starco.app.model.Client;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;


@Repository
public class ClientDaoImpl implements ClientDao{

	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addClient(Client client) throws Exception {
		sessionFactory.getCurrentSession().save(client);
		
	}

	@Override
	public List<Client> clientList() throws Exception {
		Query query =  sessionFactory.getCurrentSession().createQuery("from Client");
		List<Client> clientList = (List<Client>)query.list();
		return clientList;
	}
	
	

	
}
