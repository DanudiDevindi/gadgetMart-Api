package com.bolton.gadgetmart_main.api.repo;

import java.util.List;

import com.bolton.gadgetmart_main.api.dto.OrderDTO;

public interface OrderRepo {
	
	boolean placeOrder(OrderDTO orderDTO) throws Exception;
	
	boolean updateOrder(OrderDTO orderDTO) throws Exception;

	List<OrderDTO> getAllOrder(String userId) throws Exception;
	
	List<OrderDTO> getAllOrder() throws Exception;
}
