package com.starco.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.starco.app.model.FinishedGoods;
import com.starco.app.model.RawMaterials;
import com.starco.app.service.ProductService;

@ManagedBean(name = "productController")
@ViewScoped
@Component
public class ProductController {

	@Autowired
	private ProductService productService;

	private List<FinishedGoods> listFinishedGoods = new ArrayList<>();

	@PostConstruct
	public void init() {
		showfinishedGoods();

	}

	public void showfinishedGoods() {
		try {
			listFinishedGoods = productService.listFinishedGoodsProduct();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error occured while retrieving finished goods"));
		}
		System.out.println("Listing Finished Goods");
	}

	public void addFinishedProduct() {

	}

	public List<FinishedGoods> getListFinishedGoods() {
		return listFinishedGoods;
	}

	public void setListFinishedGoods(List<FinishedGoods> listFinishedGoods) {
		this.listFinishedGoods = listFinishedGoods;
	}

}
