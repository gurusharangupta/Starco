package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.ProductDao;
import com.starco.app.model.ManufacturedProduct;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.ProductRecipe;
import com.starco.app.model.RawMaterials;
import com.starco.app.service.ProductService;
import com.starco.app.service.RawMaterialService;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private RawMaterialService rawMaterialService;
	
	
	
	@Override
	public List<Product> listFinishedGoodsProduct() throws Exception{
		
		
		return productDao.listFinishedGoodsProduct();
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
		
	}

	@Override
	public void addManufacturedProduct(ManufacturedProduct manufacturedProduct) throws ConstraintViolationException, Exception {
		for(ProductRecipe productRecipe : manufacturedProduct.getProduct().getProductRecipe()){
			
			rawMaterialService.updateCurrentVendorForRawMaterial(productRecipe.getRawMaterialsStarco());
			
		}
		productDao.addManufacturedProduct(manufacturedProduct);
		productDao.updateProduct(manufacturedProduct.getProduct());
		
	}

	@Override
	public void addFinishedProduct(Product product)
			throws Exception {
		productDao.addFinishedProduct(product);
		
	}

	@Override
	public List<ManufacturedProduct> fetchManufacturedProductForToday() throws Exception {
		
		return productDao.fetchManufacturedProductForToday();
	}

	@Override
	public List<ManufacturedProduct> fetchManufacturedProductForMonth()
			throws Exception {
		return productDao.fetchManufacturedProductForMonth();
	}

	@Override
	public List<ManufacturedProduct> fetchManufacturedProductForQuater()
			throws Exception {
		return productDao.fetchManufacturedProductForQuater();
	}

	@Override
	public List<ManufacturedProduct> fetchManufacturedProductForYear()
			throws Exception {
		return productDao.fetchManufacturedProductForYear();
	}

	

}
