package com.bolton.gadgetmart_main.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolton.gadgetmart_main.api.dto.ProviderDTO;
import com.bolton.gadgetmart_main.api.repo.ProviderRepo;
import com.bolton.gadgetmart_main.api.repo.UserRepo;
import com.bolton.gadgetmart_main.api.util.TokenValidator;



@Controller
@CrossOrigin
@RequestMapping("/provider")
public class ServiceProviderController {
	
	
	   @Autowired
	    private UserRepo userRepo;

	    @Autowired
	    private ProviderRepo providerRepo;

	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity createProvider(@RequestHeader("Authorization") String auth, @RequestBody ProviderDTO providerDTO) {
	        boolean isValid = new TokenValidator(userRepo).validateAdminToken(auth);
	        if (!isValid) {
	            return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
	        }
	        try {
	            boolean b = providerRepo.createProvider(providerDTO);
	            return new ResponseEntity<>(b, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity getAllProviders(@RequestHeader("Authorization") String auth) {
	        boolean isValid = new TokenValidator(userRepo).validateAdminToken(auth);
	        if (!isValid) {
	            return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
	        }
	        try {
	            List<ProviderDTO> allProviders = providerRepo.getAllProviders();
	            return new ResponseEntity<>(allProviders, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity updateProvider(@RequestHeader("Authorization") String auth, @RequestBody ProviderDTO providerDTO) {
	        boolean isValid = new TokenValidator(userRepo).validateAdminToken(auth);
	        if (!isValid) {
	            return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
	        }
	        try {
	            boolean b = providerRepo.updateProvider(providerDTO);
	            return new ResponseEntity<>(b, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PatchMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity deleteProvider(@RequestHeader("Authorization") String auth, @RequestBody ProviderDTO providerDTO) {
	        boolean isValid = new TokenValidator(userRepo).validateAdminToken(auth);
	        if (!isValid) {
	            return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
	        }
	        try {
	            boolean b = providerRepo.deleteProvider(providerDTO);
	            return new ResponseEntity<>(b, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
