package com.bolton.gadgetmart_main.api.repo;

import com.bolton.gadgetmart_main.api.dto.OrderDTO;

public interface OrderRepo {
	
	boolean placeOrder(OrderDTO orderDTO) throws Exception;

}
