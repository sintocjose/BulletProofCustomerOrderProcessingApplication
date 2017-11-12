package com.bulletproof.controller;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bulletproof.model.Customer;

@RestController
public class CSVReaderController {
	
	private static final String url = "http://localhost:8081/customerService/createCustomers";

	@RequestMapping("/processCSV")
	public String processCSV() {
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withSkipHeaderRecord(true);
		List<Customer> customers = new LinkedList<>();
		try (FileReader fileReader = new FileReader(new ClassPathResource("CustomerData.csv").getFile())) {
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			int numOfRecords = csvRecords.size();
			for (int i = 0; i < numOfRecords; i++) {
				CSVRecord record = csvRecords.get(i);
				Customer customer = new Customer();
				customer.setFirstName(record.get(0));
				customer.setLastName(record.get(1));
				UUID uid = UUID.randomUUID();
				customer.setCustomerId(uid.toString());
				customers.add(customer);
				System.out.println(customer);
			}
			try {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<Object> requestEntity = new HttpEntity<Object>(customers, headers);
				restTemplate.exchange(url, HttpMethod.POST, requestEntity,
						new ParameterizedTypeReference<List<Customer>>() {
						});
				return "Success";
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Failure";
	}

}
