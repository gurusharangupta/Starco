package com.starco.app.model;

import java.io.Serializable;
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
public class RawMaterialsStarco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name="rawMaterials_id")
	private RawMaterials rawMaterials;
	
	@OneToMany(mappedBy="rawMaterialsStarco",cascade = CascadeType.REMOVE)
	private List<ProductRecipe> productRecipe;
	
	
	
	@Column
	private int moq;
	
	@Column
	private float currentQuantity;

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

	public List<ProductRecipe> getProductRecipe() {
		return productRecipe;
	}

	public void setProductRecipe(List<ProductRecipe> productRecipe) {
		this.productRecipe = productRecipe;
	}

	public float getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(float currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	

	
	
	
	
}
