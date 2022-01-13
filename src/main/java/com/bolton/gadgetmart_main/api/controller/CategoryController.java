package com.bolton.gadgetmart_main.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolton.gadgetmart_main.api.dto.CategoryDTO;
import com.bolton.gadgetmart_main.api.repo.CategoryRepo;



@Controller
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

	 @Autowired
	    private CategoryRepo categoryRepo;

	    @GetMapping(value = "/all-category", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity getAllCategories() {
	        try {
	            List<CategoryDTO> allCategories = categoryRepo.getAllCategories();
	            return new ResponseEntity<>(allCategories, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
