package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.ClientDao;
import com.starco.app.dao.PackingDao;
import com.starco.app.dao.SalesDao;
import com.starco.app.model.Client;
import com.starco.app.model.Packing;
import com.starco.app.model.Sales;
import com.starco.app.service.ClientService;
import com.starco.app.service.PackingService;
import com.starco.app.service.SalesService;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {

	
	@Autowired
	private SalesDao salesDao;

	@Override
	public void addSales(Sales sales) throws Exception {
		salesDao.addSales(sales);
		
	}

	@Override
	public List<Sales> fetchSales() throws Exception {
		
		return salesDao.fetchSales();
	}

	
	
	
	

}
