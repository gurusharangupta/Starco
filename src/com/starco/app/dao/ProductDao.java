package com.starco.app.dao;

import java.util.List;

import com.starco.app.model.FinishedGoods;
import com.starco.app.model.RawMaterials;

public interface ProductDao {

 List<FinishedGoods> listFinishedGoodsProduct() throws Exception;


}
