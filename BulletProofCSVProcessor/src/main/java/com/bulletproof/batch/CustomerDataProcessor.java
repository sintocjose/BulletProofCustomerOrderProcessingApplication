package com.bulletproof.batch;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.bulletproof.model.Customer;
public class CustomerDataProcessor implements ItemProcessor<Customer, Customer> {
    private static final Logger log = LoggerFactory.getLogger(CustomerDataProcessor.class);
    @Override
    public Customer process(final Customer customer) throws Exception {
    	log.info("CustomerDataProcessor Process Started");
    	UUID uid = UUID.randomUUID();
        customer.setCustomerId(uid.toString());
        log.info("Customer Object "+customer);
        log.info("CustomerDataProcessor Process ended");
        return customer;
    }
}