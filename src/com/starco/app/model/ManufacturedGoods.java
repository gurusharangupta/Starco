package com.starco.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MANUFACTURED_GOODS")
public class ManufacturedGoods {

	
	@Id
	@GeneratedValue
	@Column(name="MANUFACTURED_GOODS_ID")
	private int id;
	
	@Column(name="MANUFACTURED_GOODS_NAME",nullable=false)
	private String name;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "RAW_MATERIALS_USED", joinColumns = { @JoinColumn(name = "MANUFACTURED_GOODS_ID") }, inverseJoinColumns = { @JoinColumn(name = "RAW_MATERIAL_TO_MFG_ID") })
	private List<RawMaterialToMfg> listRawMaterialToMfg;
	
	@Column(name="RAW_MATERIAL_QTY",nullable=false)
	private int rawMaterialQty;
	
	@Column(name="MANUFACTURED_GOODS_COST",nullable=false)
	private long mfgGoodsCost;
	
	@Column(name="YIELD",nullable=false)
	private int yield;
	
	@Column(name="COST_PER_KG",nullable=false)
	private int costPerKg;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RawMaterialToMfg> getListRawMaterialToMfg() {
		return listRawMaterialToMfg;
	}
	public void setListRawMaterialToMfg(List<RawMaterialToMfg> listRawMaterialToMfg) {
		this.listRawMaterialToMfg = listRawMaterialToMfg;
	}
	public int getRawMaterialQty() {
		return rawMaterialQty;
	}
	public void setRawMaterialQty(int rawMaterialQty) {
		this.rawMaterialQty = rawMaterialQty;
	}
	public long getMfgGoodsCost() {
		return mfgGoodsCost;
	}
	public void setMfgGoodsCost(long mfgGoodsCost) {
		this.mfgGoodsCost = mfgGoodsCost;
	}
	public int getYield() {
		return yield;
	}
	public void setYield(int yield) {
		this.yield = yield;
	}
	public int getCostPerKg() {
		return costPerKg;
	}
	public void setCostPerKg(int costPerKg) {
		this.costPerKg = costPerKg;
	}
	
	
	
}
