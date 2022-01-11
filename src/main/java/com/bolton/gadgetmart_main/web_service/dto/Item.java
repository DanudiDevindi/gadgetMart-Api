package com.bolton.gadgetmart_main.web_service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder = {
    "id",
    "name",
    "description",
    "image",
    "price",
    "deliveryCost",
    "brand",
    "category",
    "discount",
    "shop",
    "soldOut",
    "warranty",
})

public class Item {
	
	 @XmlElement(required = true)
	    private String id;
	    @XmlElement(required = true)
	    protected String name;

	    private String description;
	    private String image;
	    private double price;
	    private double deliveryCost;
	    private String brand;
	    private String category;
	    private int discount;
	    private String shop;
	    private String warranty;
	    private boolean soldOut;

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
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

	    @Override
	    public String toString() {
	        return "Item{" +
	                "id='" + id + '\'' +
	                ", name='" + name + '\'' +
	                ", description='" + description + '\'' +
	                ", image='" + image + '\'' +
	                ", price=" + price +
	                ", deliveryCost=" + deliveryCost +
	                ", brand='" + brand + '\'' +
	                ", category='" + category + '\'' +
	                ", discount=" + discount +
	                ", shop='" + shop + '\'' +
	                ", warranty='" + warranty + '\'' +
	                ", soldOut=" + soldOut +
	                '}';
	    }
	
	

}
