package com.starco.app.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.SalesDao;
import com.starco.app.model.Sales;

@Repository
public class SalesDaoImpl implements SalesDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addSales(Sales sales) throws Exception {
		sessionFactory.getCurrentSession().save(sales);

	}

	@Override
	public List<Sales> fetchSales() throws Exception {

		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Sales");
		List<Sales> salesList = (List<Sales>) query.list();
		return salesList;
	}
}
