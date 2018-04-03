package com.starco.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT_RECIPE")
public class ProductRecipe {

	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@OneToOne(mappedBy="productRecipe",cascade = CascadeType.PERSIST)
	private RawMaterialsStarco rawMaterialsStarco;
	
	@Column
	private int quantity;
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name="product_id")
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RawMaterialsStarco getRawMaterialsStarco() {
		return rawMaterialsStarco;
	}

	public void setRawMaterialsStarco(RawMaterialsStarco rawMaterialsStarco) {
		this.rawMaterialsStarco = rawMaterialsStarco;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
