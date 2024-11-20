package com.example.demo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@ColumnDefault("0")
	private Double prise;

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	//@OneToMany(mappedBy="product_order")
	@OneToMany
	private List<ProductOrder> productOrder;
	
	
	
	
	public Product() {}
	
	public Product(String name, Double prise, Category category, List<ProductOrder> productOrder) {		
		this.name = name;
		this.prise = prise;
		this.category = category;
		this.productOrder = productOrder;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Double getPrise() {
		return prise;
	}

	public void setPrise(Double prise) {
		this.prise = prise;
	}
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	public List<ProductOrder> getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(List<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}
	

	@Override
	public String toString() {
		return "Product " + name + ", prise=" + prise;
	}	
	
	
	

}
