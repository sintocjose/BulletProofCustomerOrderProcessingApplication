package com.bulletproof.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class OrderEntity  implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    @Column(name = "ORDER_NAME")
	private String orderName;
    @ManyToOne
    @JoinColumn(name="CUST_ID", nullable = false)
    private CustomerEntity customer;
    
	public OrderEntity() {
		super();
	}
	public OrderEntity(long orderId, String orderName, CustomerEntity customer) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.customer = customer;
	}
	
	public OrderEntity(long orderId, String orderName) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
	}

	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public CustomerEntity getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
}
