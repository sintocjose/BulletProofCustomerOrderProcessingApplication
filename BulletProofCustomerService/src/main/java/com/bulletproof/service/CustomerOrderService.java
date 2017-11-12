package com.bulletproof.service;

import java.util.List;

import com.bulletproof.model.Customer;
import com.bulletproof.model.CustomerEntity;

public interface CustomerOrderService {

	void createCustomers(List<Customer> customers);

	List<Customer> getCustomers();

	void createOrder(String custId, String orderName);

	List<CustomerEntity> findByName(String name);
}
