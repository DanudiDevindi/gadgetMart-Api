package com.bolton.gadgetmart_main.java.xml.scl;

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
	    protected String id;
	    @XmlElement(required = true)
	    protected String name;
	    @XmlElement(required = true)
	    protected String price;
	    @XmlElement(required = true)
	    protected String brand;
	    @XmlElement(required = true)
	    protected String category;
	    @XmlElement(required = true)
	    protected String shop;

	    
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

	    
	    public String getPrice() {
	        return price;
	    }

	   
	    public void setPrice(String value) {
	        this.price = value;
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

	    
	    public String getShop() {
	        return shop;
	    }

	    
	    public void setShop(String value) {
	        this.shop = value;
	    }


}
