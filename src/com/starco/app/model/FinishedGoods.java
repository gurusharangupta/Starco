package com.starco.app.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FINISHED_GOODS_SELL")
public class FinishedGoods implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	@Column(name="FINISHED_GOODS_SELL_ID")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ManufacturedGoods manufacturedGoods;
	
	@Column(name="CUSTOMER_NAME",nullable=false)
	private String customerName;
	
	@Column(name="PRICE",nullable=false)
	private int price;
	
	@Column(name="QTY_SOLD",nullable=false)
	private int qtySold;
	
	@Column(name="AMOUNT_VALUE",nullable=false)
	private long amtValue;
	
	@Column(name="GST_RATE",nullable=false)
	private int gstRateApplicable;
	
	@Column(name="TOTAL_AMOUNT",nullable=false)
	private long totalAmt;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQtySold() {
		return qtySold;
	}
	public void setQtySold(int qtySold) {
		this.qtySold = qtySold;
	}
	public long getAmtValue() {
		return amtValue;
	}
	public void setAmtValue(long amtValue) {
		this.amtValue = amtValue;
	}
	public int getGstRateApplicable() {
		return gstRateApplicable;
	}
	public void setGstRateApplicable(int gstRateApplicable) {
		this.gstRateApplicable = gstRateApplicable;
	}
	public long getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(long totalAmt) {
		this.totalAmt = totalAmt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
