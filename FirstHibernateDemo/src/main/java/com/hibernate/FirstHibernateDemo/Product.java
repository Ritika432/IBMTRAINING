package com.hibernate.FirstHibernateDemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="PRODUCT_MASTER")
@NamedQueries( { @NamedQuery(name="@GET_TOTAL_COST", query="SELECT SUM(P.price) FROM Product P"),
@NamedQuery(name="@GET_PROD_NAME", query="FROM Product P ORDER BY P.price"),
@NamedQuery(name="@Get_GROUP_NAME", query="FROM Product P GROUP BY P.name"),
@NamedQuery(name="@Get_AVG_PRICE", query="SELECT AVG(P.price) FROM Product P"),
@NamedQuery(name="@Get_Name_Price_Gt", query="FROM Product P WHERE P.price>500")})
public class Product {

	// AUTO, IDENTITY, TABLE, SEQUENCE
	@GeneratedValue
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.TABLE)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="product_gen")
	//@SequenceGenerator(name="product_gen",sequenceName="prodGen")
	@Id
	@Column(name="p_id")
	private Long id;
	
	@Column(name="name")
	private String name;

	@Column(name="cost")
	private double price;
	
	public Product() {
	}
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}
	public Product(Long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String toString() {
		return id + " - " + name + " - " + price;
	}
}





/*
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
@SequenceGenerator(
    name="course_seq",
    sequenceName="course_sequence",
    allocationSize=20
)
*/