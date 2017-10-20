package com.starco.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RAW_MATERIAL_TO_MFG")
public class RawMaterialToMfg {

	@Id
	@GeneratedValue
	@Column(name="RAW_MATERIAL_TO_MFG_ID")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private RawMaterials rawMaterials;
	
	@Column(name="QTY")
	private int qty;
	
	@Column(name="COST")
	private long cost;
	
	
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	
	
}
