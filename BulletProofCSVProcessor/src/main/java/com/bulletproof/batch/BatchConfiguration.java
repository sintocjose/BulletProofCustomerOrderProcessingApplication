package com.bulletproof.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.bulletproof.model.Customer;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<Customer> reader() {
		log.info("Customer Reading Method Started");
		FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
		reader.setResource(new ClassPathResource("CustomerData.csv"));
		reader.setLineMapper(new DefaultLineMapper<Customer>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "firstName", "lastName" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {
					{
						setTargetType(Customer.class);
					}
				});
			}
		});
		log.info("Customer Reading Method Completed");
		return reader;
	}

	@Bean
	public CustomerDataProcessor processor() {
		return new CustomerDataProcessor();
	}

	@Bean
	public CustomerWriter writer() {
		CustomerWriter writer = new CustomerWriter();
		return writer;
	}

	@Bean
	public Job importCustomerJob(JobCompletionListener listener) {
		return jobBuilderFactory.get("importCustomerJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(customerProcessStep()).end().build();
	}

	@Bean
	public Step customerProcessStep() {
		return stepBuilderFactory.get("customerProcessStep").<Customer, Customer>chunk(5).reader(reader())
				.processor(processor()).writer(writer()).build();
	}
}