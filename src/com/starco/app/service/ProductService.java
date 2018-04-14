package com.starco.app.service;

import java.util.List;

import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.RawMaterials;

public interface ProductService {

	List<Product> listFinishedGoodsProduct() throws Exception;

	void addFinishedProduct(Product product) throws Exception;

	void updateProduct(Product product) throws Exception;

	

}
