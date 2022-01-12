package com.bolton.gadgetmart_main.java.xml.scl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "items"
})
@XmlRootElement(name = "ItemDetailsResponse")
public class ItemDetailsResponse {

	
	 @XmlElement(name = "Items", required = true)
	    protected Item items;

	   
	    public Item getItems() {
	        return items;
	    }

	   
	    public void setItems(Item value) {
	        this.items = value;
	    }
}
