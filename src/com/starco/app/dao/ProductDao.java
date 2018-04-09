package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.RawMaterials;

public interface ProductDao {

 List<Product> listFinishedGoodsProduct() throws Exception;

	void addFinishedProduct(Product product) throws Exception;


}
