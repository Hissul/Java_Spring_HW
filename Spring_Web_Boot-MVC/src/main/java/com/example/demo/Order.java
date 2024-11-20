package com.example.demo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private Long orderNumber;
	
	//@OneToMany(mappedBy="product_order")
	@OneToMany
	private List<ProductOrder> productOrder;
	
	
	public Order() {}
	
	public Order(Long orderNumber, List<ProductOrder> productOrder) {		
		this.orderNumber = orderNumber;
		this.productOrder = productOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	
	public List<ProductOrder> getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(List<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}

	@Override
	public String toString() {
		return "Order " + orderNumber;
	}
	

}
