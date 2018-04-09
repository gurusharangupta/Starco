package com.starco.app.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.ProductDao;
import com.starco.app.model.Packing;
import com.starco.app.model.Product;
import com.starco.app.model.RawMaterials;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> listFinishedGoodsProduct() throws Exception{
		Query query =  sessionFactory.getCurrentSession().createQuery("from Product");
		List<Product> finishedGoodsList = (List<Product>)query.list();
		return finishedGoodsList;
	}

	@Override
	public void addFinishedProduct(Product product) throws Exception{
		
		sessionFactory.getCurrentSession().save(product);
	}

	

}
