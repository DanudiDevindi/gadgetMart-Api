package com.bolton.gadgetmart_main.web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "name",
        "price",
        "brand",
        "category",
        "shop"
})
@XmlRootElement(name = "ItemDetailsRequest")
public class ItemDetailsRequest {
	
	 @XmlElement(required = true)
	    protected String name;
	    @XmlElement(required = true)
	    private String id;
	    private String price;
	    private String brand;
	    private String category;
	    @XmlElement(required = true)
	    private String shop;

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getPrice() {
	        return price;
	    }

	    public void setPrice(String price) {
	        this.price = price;
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

	    public String getShop() {
	        return shop;
	    }

	    public void setShop(String shop) {
	        this.shop = shop;
	    }

	    @Override
	    public String toString() {
	        return "ItemDetailsRequest{" +
	                "name='" + name + '\'' +
	                ", id='" + id + '\'' +
	                ", price='" + price + '\'' +
	                ", brand='" + brand + '\'' +
	                ", category='" + category + '\'' +
	                ", shop='" + shop + '\'' +
	                '}';
	    }

}
