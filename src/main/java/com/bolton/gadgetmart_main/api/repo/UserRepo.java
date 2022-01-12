package com.bolton.gadgetmart_main.api.repo;

import com.bolton.gadgetmart_main.api.dto.UserDTO;

public interface UserRepo {
	
	 UserDTO findUser(String userName, String userType) throws Exception;

	

}
