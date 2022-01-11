package com.bolton.gadgetmart_main.web_service.dto;

import java.util.List;



public class Items {
	
	  List<Item> items;

	    public List<Item> getItems() {
	        return items;
	    }

	    public void setItems(List<Item> items) {
	        this.items = items;
	    }

	    @Override
	    public String toString() {
	        return "Items{" +
	                "items=" + items +
	                '}';
	    }

}
