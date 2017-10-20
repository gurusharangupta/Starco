package com.starco.app.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.ProductDao;
import com.starco.app.model.FinishedGoods;
import com.starco.app.model.RawMaterials;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FinishedGoods> listFinishedGoodsProduct() {
		Query query =  sessionFactory.getCurrentSession().createQuery("from FinishedGoods");
		List<FinishedGoods> finishedGoodsList = (List<FinishedGoods>)query.list();
		return finishedGoodsList;
	}

	

}
