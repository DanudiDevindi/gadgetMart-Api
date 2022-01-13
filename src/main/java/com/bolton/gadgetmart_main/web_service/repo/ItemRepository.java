package com.bolton.gadgetmart_main.web_service.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bolton.gadgetmart_main.api.repo.ProviderRepo;
import com.bolton.gadgetmart_main.web_service.dto.Item;
import com.bolton.gadgetmart_main.web_service.dto.Items;



@Component
public class ItemRepository {

	
	 @Autowired
	    private ProviderRepo providerRepo;

	    @PostConstruct
	    public void initData() {

	    }

	    public ArrayList<Item> findItems() {
	        ArrayList<Item> items = new ArrayList<>();
	        RestTemplate restTemplate = new RestTemplate();
	        
	        
	        ArrayList<Item> item1 = findOrganzItems("http://localhost:8082/items");
	        ArrayList<Item> item2 = findOrganzItems("http://localhost:8083/items");
	        
	       System.out.println("Abans: "+item1.size());
	       System.out.println("Singer: "+item2.size());
	       
	       items.addAll(item1);
	       items.addAll(item2);


	        long seed = System.nanoTime();
	        Collections.shuffle(items, new Random(seed));

	        System.out.println(items.size());
	        System.out.println(items);

	        return items;
	    }
	    
	    public ArrayList<Item> findOrganzItems(String url) {
	        ArrayList<Item> items = new ArrayList<>();
	        RestTemplate restTemplate = new RestTemplate();
	        
	        try {
//	            
	            RestTemplate restTemplate1 = new RestTemplate();
	            String fooResourceUrl= url;
	            ResponseEntity<Items> response= restTemplate1.getForEntity(fooResourceUrl, Items.class);
	         
	            for(Item itemData:response.getBody().getItems()) {
	            	items.add(itemData);
	            }
	          } catch (Exception e) {
	            e.printStackTrace();
	        }  
	        return items;
	    }

}
