package com.starco.app.daoImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.SalesDao;
import com.starco.app.model.RawMaterials;
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
	public List<Sales> fetchSalesForToday() throws Exception {

		
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findSalesForToday")
		        .setParameter("todaysDate", LocalDate.now());
		
		
		
		List<Sales> salesList = (List<Sales>) query.list();
		return salesList;
	}

	@Override
	public List<Sales> fetchSalesForMonth() throws Exception {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findSalesForMonth")
		        .setParameter("todaysDate", LocalDate.now())
		        .setParameter("beforeMonthDate", LocalDate.now().minusDays(30));
		
		List<Sales> salesList = (List<Sales>) query.list();
		return salesList;
	}

	@Override
	public List<Sales> fetchSalesForQuater() throws Exception {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findSalesForQuater")
		        .setParameter("todaysDate", LocalDate.now())
		        .setParameter("beforeQuaterDate", LocalDate.now().minusDays(90));
		
		
		
		List<Sales> salesList = (List<Sales>) query.list();
		return salesList;
	}

	@Override
	public List<Sales> fetchSalesForYear() throws Exception {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findSalesForYear")
		        .setParameter("todaysDate", LocalDate.now())
		        .setParameter("beforeYearDate", LocalDate.now().minusDays(365));
		
		
		
		List<Sales> salesList = (List<Sales>) query.list();
		return salesList;
	}
	
	
	
	/*@Override
	public List<Sales> fetchSalesForToday() throws Exception {
Query query = sessionFactory.getCurrentSession().createQuery(
				"from Sales");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sales.class);
		Criterion c1 = Restrictions.ilike("saleDate", LocalDate.now());
		criteria.add(c1);
		List<Sales> salesList = (List<Sales>) criteria.list();
		return salesList;
	}*/
}
