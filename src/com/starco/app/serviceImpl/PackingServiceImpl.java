package com.starco.app.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starco.app.dao.PackingDao;
import com.starco.app.model.Packing;
import com.starco.app.service.PackingService;

@Service
@Transactional
public class PackingServiceImpl implements PackingService {

	
	@Autowired
	private PackingDao packingDao;
	
	@Override
	public void addPacking(Packing packing) throws Exception {
		
		packingDao.addPacking(packing);
		
	}

	@Override
	public List<Packing> listTypeOfPacking() throws Exception {
		
		return packingDao.listTypeOfPacking();
	}

}
