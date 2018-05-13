package com.starco.app.daoImpl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.ProductDao;
import com.starco.app.model.ManufacturedProduct;
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

	@Override
	public void updateProduct(Product product) throws Exception{
		sessionFactory.getCurrentSession().update(product);
		
	}

	@Override
	public void addManufacturedProduct(ManufacturedProduct manufacturedProduct) throws Exception{
		sessionFactory.getCurrentSession().save(manufacturedProduct);
		
	}

	@Override
	public List<ManufacturedProduct> fetchManufacturedProductForToday() throws Exception{
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findManufacturedProductForToday")
		        .setParameter("todaysDate", LocalDate.now());
		List<ManufacturedProduct> manufacturedProductList = (List<ManufacturedProduct>)query.list();
		return manufacturedProductList;
	}

	@Override
	public List<ManufacturedProduct> fetchManufacturedProductForMonth()
			throws Exception {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findManufacturedProductForMonth")
		        .setParameter("todaysDate", LocalDate.now())
		        .setParameter("beforeMonthDate", LocalDate.now().minusDays(30));
		List<ManufacturedProduct> manufacturedProductList = (List<ManufacturedProduct>)query.list();
		return manufacturedProductList;
	}

	@Override
	public List<ManufacturedProduct> fetchManufacturedProductForQuater()
			throws Exception {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findManufacturedProductForQuater")
		        .setParameter("todaysDate", LocalDate.now())
		        .setParameter("beforeQuaterDate", LocalDate.now().minusDays(90));
		List<ManufacturedProduct> manufacturedProductList = (List<ManufacturedProduct>)query.list();
		return manufacturedProductList;
	}

	@Override
	public List<ManufacturedProduct> fetchManufacturedProductForYear()
			throws Exception {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findManufacturedProductForYear")
		        .setParameter("todaysDate", LocalDate.now())
		        .setParameter("beforeYearDate", LocalDate.now().minusDays(365));
		List<ManufacturedProduct> manufacturedProductList = (List<ManufacturedProduct>)query.list();
		return manufacturedProductList;
	}

	

}
