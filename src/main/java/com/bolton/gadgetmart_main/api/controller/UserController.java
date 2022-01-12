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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolton.gadgetmart_main.api.dto.UserDTO;
import com.bolton.gadgetmart_main.api.repo.UserRepo;
import com.bolton.gadgetmart_main.api.util.TokenValidator;




@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	 @Autowired
	    private UserRepo userRepo;
	 
	 
	 @PatchMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity updateUser(@RequestHeader("Authorization") String auth,@RequestBody UserDTO userDTO) {
	        boolean isValid = new TokenValidator(userRepo).validatePublicToken(auth);
	        if (!isValid) {
	            return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
	        }
	        try {
	            boolean updated = userRepo.updateUser(userDTO);
	            return new ResponseEntity<>(updated, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	 @GetMapping(value = "/getall-users",produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity getUsers(@RequestHeader("Authorization") String auth) {
	        boolean isValid = new TokenValidator(userRepo).validateAdminToken(auth);
	        if (!isValid) {
	            return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
	        }
	        try {
	            List<UserDTO> allUsers = userRepo.getAllUsers();
	            return new ResponseEntity<>(allUsers, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
