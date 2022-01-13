package com.bolton.gadgetmart_main.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolton.gadgetmart_main.api.dto.UserDTO;
import com.bolton.gadgetmart_main.api.dto.response.StandardResponse;
import com.bolton.gadgetmart_main.api.repo.UserRepo;
import com.bolton.gadgetmart_main.api.util.JwtManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Controller
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
	
	  @Autowired
	    private UserRepo userRepo;

	    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity authenticateUser(@RequestBody String requestBody) {
	        JsonObject jsonObject = new JsonParser().parse(requestBody).getAsJsonObject();
	        String username = jsonObject.get("username").getAsString();
//	        String password = DigestUtils.md5Hex(jsonObject.get("password").getAsString());
	        String password = jsonObject.get("password").getAsString();
	        try {
	            UserDTO user = userRepo.authenticateUser(username);
	            if (user != null) {
	            System.out.println(user.getPassword());
	            System.out.println(password);
	                if (user.getPassword().equals(password)){
	                    user.setState(true);
	                    user.setMessage("User Name and Password Correct!");
	                    String jws = new JwtManager().signJWS(username, password, user.getUserType());
	                    user.setToken(jws);
	                } else {
	                    user.setState(false);
	                    user.setMessage("Password Invalid!");
	                }
	                user.setPassword(null);
	                return new ResponseEntity<>(user, HttpStatus.OK);
	            } else {
	                StandardResponse standardResponse = new StandardResponse();
	                standardResponse.setMessage("User not found");
	                return new ResponseEntity<>(standardResponse, HttpStatus.FORBIDDEN);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            StandardResponse standardResponse = new StandardResponse();
	            standardResponse.setMessage(e.getMessage());
	            return new ResponseEntity<>(standardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity createUser(@RequestBody UserDTO userResponse) {
	        try {
	            boolean added = userRepo.createUser(userResponse);
	            return new ResponseEntity<>(added, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
