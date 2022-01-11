package com.bolton.gadgetmart_main.api.dto;

public class OrderDetail {
	
	private String order_detail_id;
    private String order_id;
    private String name;
    private String description;
    private String image;
    private double price;
    private double deliveryCost;
    private String brand;
    private String category;
    private int discount;
    private String shop;
    private boolean soldOut;
    private String warranty;
    private int qty;
    
    
	public String getOrder_detail_id() {
		return order_detail_id;
	}
	
	public void setOrder_detail_id(String order_detail_id) {
		this.order_detail_id = order_detail_id;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getDeliveryCost() {
		return deliveryCost;
	}
	
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public String getShop() {
		return shop;
	}
	
	public void setShop(String shop) {
		this.shop = shop;
	}
	
	public boolean isSoldOut() {
		return soldOut;
	}
	
	public void setSoldOut(boolean soldOut) {
		this.soldOut = soldOut;
	}
	
	public String getWarranty() {
		return warranty;
	}
	
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
    
    

}
