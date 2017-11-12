package com.bulletproof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bulletproof.model.CustomerEntity;
import com.bulletproof.repository.CustomerRepository;

@RestController
public class PaginatedCustomerService {

	@Autowired
	CustomerRepository repo;

	@RequestMapping(value = "/fetchCustomerPagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<CustomerEntity> search(@RequestParam("name") String name, Pageable pageable) {
		System.out.println("Request Invoked with name="+name);
		Page<CustomerEntity> customers = repo.findByName(name, pageable);
		System.out.println(customers);
		return customers;
	}
}
