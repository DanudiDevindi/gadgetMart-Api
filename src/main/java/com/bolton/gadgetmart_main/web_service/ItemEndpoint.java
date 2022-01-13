package com.bolton.gadgetmart_main.web_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.bolton.gadgetmart_main.web_service.repo.ItemRepository;



@Endpoint
public class ItemEndpoint {
	
	private static final String NAMESPACE_URI = "http://localhost:8080/xml/items";

    private ItemRepository itemRepository;

    @Autowired
    public ItemEndpoint(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ItemDetailsRequest")
    @ResponsePayload
    public ItemDetailsResponse getStudent(@RequestPayload ItemDetailsRequest request) {
        ItemDetailsResponse response = new ItemDetailsResponse();
        response.setItems(itemRepository.findItems());
        return response;
    }

}
