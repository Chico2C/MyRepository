package com.cqg.mvcapp.dao;

import java.util.List;

import com.cqg.mvcapp.domain.Customer;

public interface CustomerDAO {
	
		public List<Customer> getForCriteriaCustomer(CriteriaCustomer cc);
	
		public List<Customer> getAll();
		
		public void save(Customer customer);
		
		public Customer get(Integer id);
		
		public void delete(Integer id);
		
		public void update(Customer customer);
		
		public long getCountWithName(String name);
}
