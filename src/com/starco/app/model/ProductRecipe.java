package com.starco.app.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT_RECIPE")
public class ProductRecipe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
	@JoinColumn(name="rawmaterialstarco_id")
	private RawMaterialsStarco rawMaterialsStarco;
	
	@Column
	private int quantity;
	
	@ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
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
