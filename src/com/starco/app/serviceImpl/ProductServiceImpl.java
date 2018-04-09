package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.ProductDao;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.RawMaterials;
import com.starco.app.service.ProductService;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> listFinishedGoodsProduct() throws Exception{
		
		
		return productDao.listFinishedGoodsProduct();
	}

	@Override
	public void addFinishedProduct(Product product) throws Exception {
		productDao.addFinishedProduct(product);
		
	}

	

}
