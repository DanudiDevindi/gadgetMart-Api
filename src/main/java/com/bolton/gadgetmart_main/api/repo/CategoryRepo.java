package com.bolton.gadgetmart_main.api.repo;

import java.util.List;

import com.bolton.gadgetmart_main.api.dto.CategoryDTO;


public interface CategoryRepo {
	
	List<CategoryDTO> getAllCategories() throws Exception;

}
