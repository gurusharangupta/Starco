package com.starco.app.service;

import java.util.List;

import com.starco.app.model.Sales;

public interface SalesService {

	public void addSales(Sales sales) throws Exception;

	public List<Sales> fetchSalesForToday() throws Exception;

}
