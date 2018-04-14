package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.Sales;

public interface SalesDao {

	void addSales(Sales sales) throws Exception;

	List<Sales> fetchSales() throws Exception;

}
