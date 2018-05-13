package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.ManufacturedProduct;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.RawMaterials;

public interface ProductDao {

 List<Product> listFinishedGoodsProduct() throws Exception;

	void addFinishedProduct(Product product) throws Exception;

	void updateProduct(Product product) throws Exception;

	void addManufacturedProduct(ManufacturedProduct manufacturedProduct) throws Exception;

	List<ManufacturedProduct> fetchManufacturedProductForToday() throws Exception;
	
	List<ManufacturedProduct> fetchManufacturedProductForMonth() throws Exception;
	
	List<ManufacturedProduct> fetchManufacturedProductForQuater() throws Exception;
	
	List<ManufacturedProduct> fetchManufacturedProductForYear() throws Exception;


}
