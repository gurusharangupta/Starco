package com.starco.app.daoImpl;

import java.util.List;

import javax.ejb.DuplicateKeyException;

import org.hibernate.Criteria;
import org.hibernate.DuplicateMappingException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starco.app.dao.RawMaterialDao;
import com.starco.app.model.RawMaterials;
import com.starco.app.model.RawMaterialsStarco;

@Repository
public class RawMaterialDaoImpl implements RawMaterialDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RawMaterials> listOfRawMaterials() throws Exception{
		Query query =  sessionFactory.getCurrentSession().createQuery("from RawMaterials");
		List<RawMaterials> rawMaterialsList = (List<RawMaterials>)query.list();
		return rawMaterialsList;
	}

	@Override
	public void addRawMaterial(RawMaterials rawMaterials) throws ConstraintViolationException,Exception{
		sessionFactory.getCurrentSession().save(rawMaterials);
		
	}

	@Override
	public List<String> getUniqueRawMaterialList(String query) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RawMaterials.class);
		criteria.setProjection(Projections.distinct(Projections.property("name")));
		criteria.setFetchSize(10);
		Criterion c1 = Restrictions.like("name", query,MatchMode.START);
		criteria.add(c1);
		List<String> msgFromList = criteria.list();
		return msgFromList;
	}

	@Override
	public List<RawMaterials> getNamedRawMaterialList(String rawMaterial) throws Exception{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RawMaterials.class);
		Criterion c1 = Restrictions.like("name", rawMaterial,MatchMode.EXACT);
		criteria.add(c1);
		List<RawMaterials> msgFromList = criteria.list();
		return msgFromList;
	}

	@Override
	public void updateCurrentVendorForRawMaterial(
			RawMaterialsStarco rawMaterialsStarco) throws ConstraintViolationException,Exception{
		RawMaterialsStarco currentRawMaterialStarco =  getCurrentVendorForRawMaterial(rawMaterialsStarco.getRawMaterials().getName());
		if(currentRawMaterialStarco.getRawMaterials()!=null && currentRawMaterialStarco.getRawMaterials().getName()!=null && !currentRawMaterialStarco.getRawMaterials().getName().isEmpty()){
			currentRawMaterialStarco.setMoq(rawMaterialsStarco.getMoq());
			currentRawMaterialStarco.setCurrentQuantity(rawMaterialsStarco.getCurrentQuantity());
			currentRawMaterialStarco.setRawMaterials(rawMaterialsStarco.getRawMaterials());
			sessionFactory.getCurrentSession().update(currentRawMaterialStarco);
			sessionFactory.getCurrentSession().flush();
		}else{
		sessionFactory.getCurrentSession().save(rawMaterialsStarco);
		}
		
	}

	@Override
	public RawMaterialsStarco getCurrentVendorForRawMaterial(String rawMaterial)throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RawMaterialsStarco.class);
		RawMaterialsStarco rawMaterialStarco = new RawMaterialsStarco();
		  
		Criterion c1 = Restrictions.like("name", rawMaterial,MatchMode.EXACT);
		criteria.createCriteria("rawMaterials")
        .add(c1);
		if(criteria.list().size()!=0){
		rawMaterialStarco = (RawMaterialsStarco)criteria.list().get(0);
		}
		return rawMaterialStarco;
		
	}

	@Override
	public List<RawMaterialsStarco> fetchRawMaterialStarco() throws Exception{
		Query query =  sessionFactory.getCurrentSession().createQuery("from RawMaterialsStarco");
		List<RawMaterialsStarco> rawMaterialsStarcoList = (List<RawMaterialsStarco>)query.list();
		return rawMaterialsStarcoList;
	}

	@Override
	public void updateRawMaterial(RawMaterials rawMaterials) throws ConstraintViolationException,Exception{
		sessionFactory.getCurrentSession().update(rawMaterials);
		sessionFactory.getCurrentSession().flush();
	}

	
	
	
}
