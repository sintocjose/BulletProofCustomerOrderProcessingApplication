package com.bulletproof.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bulletproof.model.Customer;
import com.bulletproof.model.CustomerEntity;
import com.bulletproof.model.OrderEntity;
import com.bulletproof.repository.CustomerRepository;
import com.bulletproof.repository.OrderRepository;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService{

	private static final Logger log = LoggerFactory.getLogger(CustomerOrderServiceImpl.class);
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Override
	public void createCustomers(List<Customer> customers) {
		log.info("Invoked createCustomers");
		List<CustomerEntity> entities = new LinkedList<>();
		customers.stream().forEach(cust->{
			CustomerEntity entity = new CustomerEntity();
			entity.setCustomerId(cust.getCustomerId());
			entity.setFirstName(cust.getFirstName());
			entity.setLastName(cust.getLastName());
			entities.add(entity);
		});
		customerRepository.save(entities);
		log.info("completed createCustomers and persisted list of customers "+entities);
	}

	@Override
	public List<Customer> getCustomers() {
		log.info("Invoked getCustomers");
		List<Customer> listofCustomer = new LinkedList<>();
		customerRepository.findAll().stream().forEach(cust->{
			Customer customer = new Customer();
			customer.setCustomerId(cust.getCustomerId());
			customer.setFirstName(cust.getFirstName());
			customer.setLastName(cust.getLastName());
			listofCustomer.add(customer);
		});
		log.info("Ended getCustomers"+listofCustomer);
		return listofCustomer;
	}

	@Override
	public boolean createOrder(String custId, String orderName) {
		log.info("created order for cust id"+custId+" order name"+orderName);	
		OrderEntity entity = new OrderEntity();
		entity.setOrderName(orderName);
		CustomerEntity customer = new CustomerEntity();
		customer.setCustomerId(custId);
		entity.setCustomer(customer);
		orderRepository.save(entity);
		log.info("Persisted Order Entity"+entity);
		return true;
	}

	@Override
	public List<CustomerEntity> findByName(String name) {
		System.out.println("find by name="+name);
		return customerRepository.findCustomerByName(name.toLowerCase());
	}

}
