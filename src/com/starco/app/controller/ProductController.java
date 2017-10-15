package com.starco.app.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.starco.app.service.ProductService;

@ManagedBean(name="productController")
@ViewScoped
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	public String getHello() {
		return "Hello from PrimeFaces and Spring Boot!";
	}

}
