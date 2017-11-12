package com.bulletproof.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.bulletproof.model.CustomerEntity;
public interface CustomerRepository  extends JpaRepository<CustomerEntity,String> {
	//@Query(value = "from CustomerEntity cust inner join cust.orders ord where lower(cust.firstName) like %:name% or lower(cust.lastName) like %:name%  ")
	@Query(value = "from CustomerEntity cust left join cust.orders ord where lower(cust.firstName) like %:name% or lower(cust.lastName) like %:name%  ")
	List<CustomerEntity> findCustomerByName(@Param("name") String name);
}
