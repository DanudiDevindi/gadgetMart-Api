package com.bolton.gadgetmart_main.api.util;

import java.util.Base64;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bolton.gadgetmart_main.api.dto.UserDTO;
import com.bolton.gadgetmart_main.api.repo.UserRepo;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class TokenValidator {
	
	 private static Logger LOGGER = LogManager.getLogger(TokenValidator.class);

	    private UserRepo userRepo;

	    public TokenValidator() {
	    }

	    public TokenValidator(UserRepo userRepo) {
	        this.userRepo = userRepo;
	    }

	    public boolean validateAdminToken(String auth) {
	        try {
	            String payload = new String(Base64.getDecoder().decode(auth.split("\\.")[1]));

	            String sub = new JsonParser().parse(payload).getAsJsonObject().get("sub").getAsString();
	            String userType = new JsonParser().parse(payload).getAsJsonObject().get("userType").getAsString();

	            if (userType.equals("ADMIN")) {
	                return checkUser(sub, auth);
	            } else {
	                return false;
	            }
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean validatePublicToken(String auth) {
	        try {
	            String payload = new String(Base64.getDecoder().decode(auth.split("\\.")[1]));
	            String sub = new JsonParser().parse(payload).getAsJsonObject().get("sub").getAsString();
	            return checkUser(sub, auth);
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            return false;
	        }
	    }

	    public boolean validatePublicTokenAndUser(String auth, String userId) {
	        try {
	            String payload = new String(Base64.getDecoder().decode(auth.split("\\.")[1]));
	            String sub = new JsonParser().parse(payload).getAsJsonObject().get("sub").getAsString();
	            return checkUser(sub, auth, userId);
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            return false;
	        }
	    }

	    private boolean checkUser(String sub, String auth) throws Exception {
	        UserDTO user = userRepo.findUser(sub, null);
	        if (null == user) {
	            LOGGER.info("User not found for username in token");
	            return false;
	        }
	        String token = auth.split(" ")[1];
	        Jws<Claims> claimsJws = new JwtManager().verifyJWS(token, user.getUserName(), user.getPassword());
	        return (claimsJws != null);
	    }

	    private boolean checkUser(String sub, String auth, String userId) throws Exception {
	        UserDTO user = userRepo.findUser(sub, null);
	        if (null == user) {
	            LOGGER.info("User not found for username in token");
	            return false;
	        } else if (!(user.getUserId()+"").equals(userId)) {
	            LOGGER.info("Unauthorized");
	            return false;
	        }
	        String token = auth.split(" ")[1];
	        Jws<Claims> claimsJws = new JwtManager().verifyJWS(token, user.getUserName(), user.getPassword());
	        return (claimsJws != null);
	    }

}
