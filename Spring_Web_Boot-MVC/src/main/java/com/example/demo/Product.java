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
	
	
	public Product() {}

	public Product(String name, Double prise) {
		this.name = name;
		this.prise = prise;
	}

	


	@Override
	public String toString() {
		return "Product : " + name + " , prise : " + prise;
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

}
