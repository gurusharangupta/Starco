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
	
	@Autowired
	private PackingService packingService;

	@Override
	public void addSales(Sales sales,Packing updatedPacking) throws Exception {
		salesDao.addSales(sales);
		packingService.updatePacking(updatedPacking);
		
		
	}

	@Override
	public List<Sales> fetchSalesForToday() throws Exception {
		
		return salesDao.fetchSalesForToday();
	}

	@Override
	public List<Sales> fetchSalesForMonth() throws Exception {
		return salesDao.fetchSalesForMonth();
	}

	@Override
	public List<Sales> fetchSalesForQuater() throws Exception {
		return salesDao.fetchSalesForQuater();
	}

	@Override
	public List<Sales> fetchSalesForYear() throws Exception {
		return salesDao.fetchSalesForYear();
	}

	
	
	
	

}
