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
	 
	

}
