package com.bulletproof.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.bulletproof.model.Customer;

public class CustomerWriter implements ItemWriter<Customer> {
	private static final Logger log = LoggerFactory.getLogger(CustomerWriter.class);
	private static final String url = "http://localhost:8081/customerService/createCustomers";

	@Override
	public void write(List<? extends Customer> customers) throws Exception {
		try {
			for (Customer customer : customers) {
				log.info("Posting the Data " + customer);
			}
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(customers, headers);
			restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<List<Customer>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
