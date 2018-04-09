package com.starco.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class Product {

	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column
	private String productName;
	
	@Column
	private float price;
	
	@Column
	private int moq;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ProductRecipe> productRecipe;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<ClientProductStarco> clientProductStarcoList;
	
	
	@Column
	private float materialCost;
	
	@Column
	private float costOfEnergyAndLabor;
	
	@Column
	private float costOfPacking;
	
	@Column
	private float totalCost;
	
	@Column
	private float priceForExporter;
	
	@Column
	private float priceForDealer;
	
	@Column
	private float priceForCustomer;
	
	@Column
	private float priceInCash;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getMoq() {
		return moq;
	}
	public void setMoq(int moq) {
		this.moq = moq;
	}
	public Set<ProductRecipe> getProductRecipe() {
		return productRecipe;
	}
	public void setProductRecipe(Set<ProductRecipe> productRecipe) {
		this.productRecipe = productRecipe;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(float materialCost) {
		this.materialCost = materialCost;
	}
	public float getCostOfEnergyAndLabor() {
		return costOfEnergyAndLabor;
	}
	public void setCostOfEnergyAndLabor(float costOfEnergyAndLabor) {
		this.costOfEnergyAndLabor = costOfEnergyAndLabor;
	}
	public float getCostOfPacking() {
		return costOfPacking;
	}
	public void setCostOfPacking(float costOfPacking) {
		this.costOfPacking = costOfPacking;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public float getPriceForExporter() {
		return priceForExporter;
	}
	public void setPriceForExporter(float priceForExporter) {
		this.priceForExporter = priceForExporter;
	}
	public float getPriceForDealer() {
		return priceForDealer;
	}
	public void setPriceForDealer(float priceForDealer) {
		this.priceForDealer = priceForDealer;
	}
	public float getPriceForCustomer() {
		return priceForCustomer;
	}
	public void setPriceForCustomer(float priceForCustomer) {
		this.priceForCustomer = priceForCustomer;
	}
	public float getPriceInCash() {
		return priceInCash;
	}
	public void setPriceInCash(float priceInCash) {
		this.priceInCash = priceInCash;
	}
	public Set<ClientProductStarco> getClientProductStarcoList() {
		return clientProductStarcoList;
	}
	public void setClientProductStarcoList(
			Set<ClientProductStarco> clientProductStarcoList) {
		this.clientProductStarcoList = clientProductStarcoList;
	}
	
}
