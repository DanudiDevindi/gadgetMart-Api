package com.bolton.gadgetmart_main.java.xml.scl;

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
    "warranty"
})

public class Item {
	
	 @XmlElement(required = true)
	    protected String id;
	    @XmlElement(required = true)
	    protected String name;
	    @XmlElement(required = true)
	    protected String description;
	    @XmlElement(required = true)
	    protected String image;
	    protected double price;
	    protected double deliveryCost;
	    @XmlElement(required = true)
	    protected String brand;
	    @XmlElement(required = true)
	    protected String category;
	    protected int discount;
	    @XmlElement(required = true)
	    protected String shop;
	    protected boolean soldOut;
	    @XmlElement(required = true)
	    protected String warranty;

	   
	    public String getId() {
	        return id;
	    }

	   
	    public void setId(String value) {
	        this.id = value;
	    }

	   
	    public String getName() {
	        return name;
	    }

	   
	    public void setName(String value) {
	        this.name = value;
	    }

	    
	    public String getDescription() {
	        return description;
	    }

	    
	    public void setDescription(String value) {
	        this.description = value;
	    }

	    
	    public String getImage() {
	        return image;
	    }

	    
	    public void setImage(String value) {
	        this.image = value;
	    }

	   
	    public double getPrice() {
	        return price;
	    }

	    
	    public void setPrice(double value) {
	        this.price = value;
	    }

	   
	    public double getDeliveryCost() {
	        return deliveryCost;
	    }

	    
	    public void setDeliveryCost(double value) {
	        this.deliveryCost = value;
	    }

	   
	    public String getBrand() {
	        return brand;
	    }

	   
	    public void setBrand(String value) {
	        this.brand = value;
	    }

	   
	    public String getCategory() {
	        return category;
	    }

	    
	    public void setCategory(String value) {
	        this.category = value;
	    }

	   
	    public int getDiscount() {
	        return discount;
	    }

	    
	    public void setDiscount(int value) {
	        this.discount = value;
	    }

	    
	    public String getShop() {
	        return shop;
	    }

	   
	    public void setShop(String value) {
	        this.shop = value;
	    }

	    
	    public boolean isSoldOut() {
	        return soldOut;
	    }

	    
	    public void setSoldOut(boolean value) {
	        this.soldOut = value;
	    }

	    
	    public String getWarranty() {
	        return warranty;
	    }

	   
	    public void setWarranty(String value) {
	        this.warranty = value;
	    }


}
