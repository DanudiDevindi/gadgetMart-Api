package com.bolton.gadgetmart_main.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolton.gadgetmart_main.api.dto.OrderDTO;
import com.bolton.gadgetmart_main.api.repo.OrderRepo;
import com.bolton.gadgetmart_main.api.repo.UserRepo;
import com.bolton.gadgetmart_main.api.util.TokenValidator;



@Controller
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

	
	
	 @Autowired
	    private OrderRepo orderRepo;

	    @Autowired
	    private UserRepo userRepo;
	    
	    
	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity placeOrder(@RequestHeader("Authorization") String auth, @RequestBody OrderDTO orderDTO) {
	        boolean isValid = new TokenValidator(userRepo).validatePublicToken(auth);
	        if (!isValid) {
	            return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
	        }
	        try {
	            boolean b = orderRepo.placeOrder(orderDTO);
	            return new ResponseEntity<>(b, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity updateOrder(@RequestHeader("Authorization") String auth, @RequestBody OrderDTO orderDTO) {
	        boolean isValid = new TokenValidator(userRepo).validatePublicToken(auth);
	        if (!isValid) {
	            return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
	        }
	        try {
	            boolean b = orderRepo.updateOrder(orderDTO);
	            return new ResponseEntity<>(b, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
