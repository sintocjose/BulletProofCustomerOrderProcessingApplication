package com.bulletproof.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bulletproof.model.Customer;
import com.bulletproof.model.CustomerEntity;
import com.bulletproof.service.CustomerOrderService;

@RestController
public class CustomerOrderEndpoint {

	@Autowired
	CustomerOrderService customerOrderService;

	@RequestMapping(value = "customerService/createCustomers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseStatus(HttpStatus.CREATED) void createCustomers(@RequestBody List<Customer> customers) {
		customerOrderService.createCustomers(customers);
	}

	@RequestMapping(value = "customerService/fetchCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Customer> fetchCustomers() {
		return customerOrderService.getCustomers();
	}

	@RequestMapping(value = "customerService/createOrder/{custId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseStatus(HttpStatus.CREATED) void createOrder(@PathVariable("custId") String customerId,
			@RequestBody String orderName) {
		customerOrderService.createOrder(customerId, orderName);
	}

	@RequestMapping(method = RequestMethod.GET, value = "customerService/findByName")
	@ResponseBody
	public List<CustomerEntity> search(@RequestParam(value = "name") String name) {
		return customerOrderService.findByName(name);
	}
}
