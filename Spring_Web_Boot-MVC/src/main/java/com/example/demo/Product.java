package com.example.demo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
@Table(name="user_products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@ColumnDefault("0")
	private Double prise;
	

	@ManyToOne(optional = true)
	@JoinColumn(name="consumer_id")
	private Consumer consumer;
	
	
	
	public Product() {}

	public Product(String name, Double prise, Consumer consumer) {		
		this.name = name;
		this.prise = prise;
		this.consumer = consumer;
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

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}


}
