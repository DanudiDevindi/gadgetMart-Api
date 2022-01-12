package com.bolton.gadgetmart_main.java.xml.scl;

import javax.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {
	
	
    public ObjectFactory() {
    }

   
    public ItemDetailsRequest createItemDetailsRequest() {
        return new ItemDetailsRequest();
    }

    
    public ItemDetailsResponse createItemDetailsResponse() {
        return new ItemDetailsResponse();
    }

    
    public Item createItem() {
        return new Item();
    }


}
