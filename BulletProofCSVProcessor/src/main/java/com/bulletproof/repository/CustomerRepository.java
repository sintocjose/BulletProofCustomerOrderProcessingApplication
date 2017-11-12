package com.bulletproof.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bulletproof.model.CustomerEntity;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, String> {
	  //  @RestResource
	@Query(value = "from CustomerEntity cust  where lower(cust.firstName) like %:name% or lower(cust.lastName) like %:name%  ")
	Page<CustomerEntity> findByName(@Param("name") String name, Pageable pageable);
}
