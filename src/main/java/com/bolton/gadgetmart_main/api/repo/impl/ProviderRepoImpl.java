package com.bolton.gadgetmart_main.api.repo.impl;



import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;

import com.bolton.gadgetmart_main.api.db.DBConnection;
import com.bolton.gadgetmart_main.api.dto.ProviderDTO;
import com.bolton.gadgetmart_main.api.repo.ProviderRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProviderRepoImpl implements ProviderRepo {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    @Override
    public boolean createProvider(ProviderDTO providerDTO) throws Exception{
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "INSERT INTO service_provider(name, url) VALUES (?,?) ";

        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, providerDTO.getName());
        preparedStatement.setString(2, providerDTO.getUrl());
        int i = preparedStatement.executeUpdate();
        closeConnection();
        return i > 0;
    }

    @Override
    public List<ProviderDTO> getAllProviders() throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "select * from service_provider";

        preparedStatement = connection.prepareStatement(SQL);
        resultSet = preparedStatement.executeQuery();

        List<ProviderDTO> providerDTOS = new ArrayList<>();

        while (resultSet.next()) {
            ProviderDTO providerDTO = new ProviderDTO();
            providerDTO.setProviderId(resultSet.getInt(1));
            providerDTO.setName(resultSet.getString(2));
            providerDTO.setUrl(resultSet.getString(3));
            providerDTOS.add(providerDTO);
        }
        closeConnection();
        return providerDTOS;
    }

    @Override
    public boolean updateProvider(ProviderDTO providerDTO) throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "update service_provider set name=?, url=? where provider_id=?";

        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, providerDTO.getName());
        preparedStatement.setString(2, providerDTO.getUrl());
        preparedStatement.setInt(3, providerDTO.getProviderId());
        int i = preparedStatement.executeUpdate();
        closeConnection();
        return i > 0;
    }

    @Override
    public boolean deleteProvider(ProviderDTO providerDTO) throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "delete from service_provider where provider_id=?";

        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, providerDTO.getProviderId());
        int i = preparedStatement.executeUpdate();
        closeConnection();
        return i > 0;
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
