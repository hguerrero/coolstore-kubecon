package com.redhat.cloudnative;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Cacheable
public class Product extends PanacheEntity {

	public String itemId;
	public String name;
	public double price;
	public int quantity;

	public Product() {}

}
