package com.bolton.gadgetmart_main.api.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;

import com.bolton.gadgetmart_main.api.db.DBConnection;
import com.bolton.gadgetmart_main.api.dto.UserDTO;
import com.bolton.gadgetmart_main.api.repo.UserRepo;

@Repository
public class UserRepoImpl implements UserRepo {
	
	 private static Connection connection;
	 private static PreparedStatement preparedStatement;
	 private static ResultSet resultSet;

	 @Override
	    public UserDTO findUser(String userName, String userType) throws Exception {
	        connection = DBConnection.getDBConnection().getConnection();
	        String SQL1 = "select * from user_login where userName=?";
	        if (userType != null) SQL1 = "select * from user_login where userName=? && userType=?";

	        preparedStatement = connection.prepareStatement(SQL1);
	        preparedStatement.setString(1, userName);
	        if (userType != null) preparedStatement.setString(2, userType);
	        resultSet = preparedStatement.executeQuery();
	        UserDTO userResponse = null;
	        if (resultSet.next()) {
	            userResponse = new UserDTO();
	            userResponse.setUserId(resultSet.getInt(1));
	            userResponse.setName(resultSet.getString(2));
	            userResponse.setUserType(resultSet.getString(3));
	            userResponse.setUserName(resultSet.getString(4));
	            userResponse.setPassword(resultSet.getString(5));
	            userResponse.setAddress(resultSet.getString(6));
	            userResponse.setContact(resultSet.getString(7));
	            userResponse.setEmail(resultSet.getString(8));
	        }
	        closeConnection();
	        return userResponse;
	    }
	
	   private void closeConnection() {
	        try {
	            DbUtils.closeQuietly(resultSet);
	            DbUtils.closeQuietly(preparedStatement);
	            DbUtils.close(connection);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	

}
