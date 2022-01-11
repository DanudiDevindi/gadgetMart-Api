package com.bolton.gadgetmart_main.api.dto;

import java.util.List;

public class CategoryDTO {
	
	 private int id;
	 private String name;
	 private List<String> brands;
	 
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
	
	public List<String> getBrands() {
		return brands;
	}
	
	public void setBrands(List<String> brands) {
		this.brands = brands;
	}
	 
	 


}
