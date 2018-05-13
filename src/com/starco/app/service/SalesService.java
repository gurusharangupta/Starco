package com.starco.app.service;

import java.util.List;

import com.starco.app.model.Packing;
import com.starco.app.model.Sales;

public interface SalesService {

	public void addSales(Sales sales, Packing updatedPacking) throws Exception;

	public List<Sales> fetchSalesForToday() throws Exception;

	List<Sales> fetchSalesForMonth() throws Exception;

	List<Sales> fetchSalesForQuater() throws Exception;

	List<Sales> fetchSalesForYear() throws Exception;

}
