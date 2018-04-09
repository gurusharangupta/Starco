package com.starco.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="RAW_MATERIAL_STARCO_IN_USE",uniqueConstraints = {
        @UniqueConstraint(columnNames =  {"rawMaterials_id"})})
public class RawMaterialsStarco {

	
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column(unique=true)
	private String name;
	
	@Column
	private float price;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name="rawMaterials_id")
	private RawMaterials rawMaterials;
	
	@OneToMany(mappedBy="rawMaterialsStarco",cascade = CascadeType.REMOVE)
	private List<ProductRecipe> productRecipe;
	
	
	
	@Column
	private int moq;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RawMaterials getRawMaterials() {
		return rawMaterials;
	}

	public void setRawMaterials(RawMaterials rawMaterials) {
		this.rawMaterials = rawMaterials;
	}

	public int getMoq() {
		return moq;
	}

	public void setMoq(int moq) {
		this.moq = moq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<ProductRecipe> getProductRecipe() {
		return productRecipe;
	}

	public void setProductRecipe(List<ProductRecipe> productRecipe) {
		this.productRecipe = productRecipe;
	}

	

	
	
	
	
}
