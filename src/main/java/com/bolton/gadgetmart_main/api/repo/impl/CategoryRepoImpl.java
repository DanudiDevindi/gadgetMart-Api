package com.bolton.gadgetmart_main.api.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;

import com.bolton.gadgetmart_main.api.db.DBConnection;
import com.bolton.gadgetmart_main.api.dto.CategoryDTO;
import com.bolton.gadgetmart_main.api.repo.CategoryRepo;


@Repository
public class CategoryRepoImpl implements CategoryRepo{
	
	private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static ResultSet resultSet1;

    @Override
    public List<CategoryDTO> getAllCategories() throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "select * from category";

        preparedStatement = connection.prepareStatement(SQL);
        resultSet = preparedStatement.executeQuery();
        List<CategoryDTO> designations = new ArrayList<>();
        while (resultSet.next()) {

            String SQL2 = "select * from category_brand where category_brand.category_id = ?";
            preparedStatement = connection.prepareStatement(SQL2);
            preparedStatement.setInt(1, resultSet.getInt(1));
            resultSet1 = preparedStatement.executeQuery();
            List<String> brands = new ArrayList<>();
            while (resultSet1.next()) {
                brands.add(resultSet1.getString(3));
            }

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(resultSet.getInt(1));
            categoryDTO.setName(resultSet.getString(2));
            categoryDTO.setBrands(brands);
            designations.add(categoryDTO);
        }
        closeConnection();
        return designations;
    }

    private void closeConnection() {
        try {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(resultSet1);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.close(connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
