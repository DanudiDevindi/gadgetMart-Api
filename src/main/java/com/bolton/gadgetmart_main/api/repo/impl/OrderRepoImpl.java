package com.bolton.gadgetmart_main.api.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Repository;

import com.bolton.gadgetmart_main.api.db.DBConnection;
import com.bolton.gadgetmart_main.api.dto.OrderDTO;
import com.bolton.gadgetmart_main.api.dto.OrderDetail;
import com.bolton.gadgetmart_main.api.repo.OrderRepo;

@Repository
public class OrderRepoImpl implements OrderRepo {
	
	private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static ResultSet resultSet1;
    
    
    @Override
    public boolean placeOrder(OrderDTO orderDTO) throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "INSERT INTO orders (user_id, address, contact,paymentMethod,totalCost) VALUES (?,?,?,?,?)";
        connection.setAutoCommit(false);

        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, orderDTO.getUser_id());
        preparedStatement.setString(2, orderDTO.getAddress());
        preparedStatement.setString(3, orderDTO.getContact());
        preparedStatement.setString(4, orderDTO.getPaymentMethod());
        preparedStatement.setDouble(5, orderDTO.getTotalCost());
        int i = preparedStatement.executeUpdate();
        if (i > 0){
            preparedStatement = connection.prepareStatement("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int k = 0;
                orderDTO.setOrder_id(resultSet.getInt(1));
                List<OrderDetail> orderDetails = orderDTO.getOrderDetail();
                for (OrderDetail orderDetail : orderDetails) {
                    preparedStatement = connection.prepareStatement("INSERT INTO order_detail (order_id,name," +
                            "description,image,price,deliveryCost,brand,category,discount,shop,soldOut,warranty,qty) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");

                    preparedStatement.setInt(1, orderDTO.getOrder_id());
                    preparedStatement.setString(2, orderDetail.getName());
                    preparedStatement.setString(3, orderDetail.getDescription());
                    preparedStatement.setString(4, orderDetail.getImage());
                    preparedStatement.setDouble(5, orderDetail.getPrice());
                    preparedStatement.setDouble(6, orderDetail.getDeliveryCost());
                    preparedStatement.setString(7, orderDetail.getBrand());
                    preparedStatement.setString(8, orderDetail.getCategory());
                    preparedStatement.setInt(9, orderDetail.getDiscount());
                    preparedStatement.setString(10, orderDetail.getShop());
                    preparedStatement.setBoolean(11, orderDetail.isSoldOut());
                    preparedStatement.setString(12, orderDetail.getWarranty());
                    preparedStatement.setInt(13, orderDetail.getQty());
                    k = preparedStatement.executeUpdate();
                }
                connection.commit();
                closeConnection();
                return k > 0;
            } else {
                closeConnection();
                return false;
            }
        } else {
            closeConnection();
            return false;
        }
    }
    
    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "update orders set status=? where order_id=?";
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, orderDTO.getStatus());
        preparedStatement.setInt(2, orderDTO.getOrder_id());
        int i = preparedStatement.executeUpdate();
        closeConnection();
        return i > 0;
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
