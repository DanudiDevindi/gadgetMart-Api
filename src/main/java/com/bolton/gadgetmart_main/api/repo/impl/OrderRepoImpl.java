package com.bolton.gadgetmart_main.api.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    
    @Override
    public List<OrderDTO> getAllOrder(String userId) throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "select * from orders where user_id=? ";
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, userId);
        resultSet = preparedStatement.executeQuery();

        ArrayList<OrderDTO> orders = getOrders(false);
        closeConnection();
        return orders;
    }
    
    public ArrayList<OrderDTO> getOrders(boolean status) throws Exception{
        ArrayList<OrderDTO> orders = new ArrayList<>();
        while (resultSet.next()) {

            String SQL2 = "select * from order_detail where order_detail.order_id = ?";
            preparedStatement = connection.prepareStatement(SQL2);
            preparedStatement.setInt(1, resultSet.getInt(1));
            resultSet1 = preparedStatement.executeQuery();

            List<OrderDetail> orderDetails = new ArrayList<>();
            while (resultSet1.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder_detail_id(resultSet1.getString(1));
                orderDetail.setOrder_id(resultSet1.getString(2));
                orderDetail.setName(resultSet1.getString(3));
                orderDetail.setDescription(resultSet1.getString(4));
                orderDetail.setImage(resultSet1.getString(5));
                orderDetail.setPrice(resultSet1.getDouble(6));
                orderDetail.setDeliveryCost(resultSet1.getDouble(7));
                orderDetail.setBrand(resultSet1.getString(8));
                orderDetail.setCategory(resultSet1.getString(9));
                orderDetail.setDiscount(resultSet1.getInt(10));
                orderDetail.setShop(resultSet1.getString(11));
                orderDetail.setSoldOut(resultSet1.getBoolean(12));
                orderDetail.setWarranty(resultSet1.getString(13));
                orderDetail.setQty(resultSet1.getInt(14));
                orderDetails.add(orderDetail);
            }

            OrderDTO order = new OrderDTO();
            order.setOrder_id(resultSet.getInt(1));
            if (status){
                order.setName(resultSet.getString(2));
            } else {
                order.setUser_id(resultSet.getInt(2));
            }
            order.setAddress(resultSet.getString(3));
            order.setContact(resultSet.getString(4));
            order.setPaymentMethod(resultSet.getString(5));
            order.setTotalCost(resultSet.getDouble(6));
            order.setStatus(resultSet.getString(7));
            order.setOrderDetail(orderDetails);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public List<OrderDTO> getAllOrder() throws Exception {
        connection = DBConnection.getDBConnection().getConnection();
        String SQL = "select o.order_id,u.name,o.address,o.contact,o.paymentMethod,o.totalCost,o.status from orders o, " +
                "user_login u where o.user_id = u.user_id order by order_id desc ";
        preparedStatement = connection.prepareStatement(SQL);
        resultSet = preparedStatement.executeQuery();

        ArrayList<OrderDTO> orders = getOrders(true);
        closeConnection();
        return orders;
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
