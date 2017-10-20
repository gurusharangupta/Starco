package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.ProductDao;
import com.starco.app.model.FinishedGoods;
import com.starco.app.model.RawMaterials;
import com.starco.app.service.ProductService;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<FinishedGoods> listFinishedGoodsProduct() {
		
		
		return productDao.listFinishedGoodsProduct();
	}

	

}
