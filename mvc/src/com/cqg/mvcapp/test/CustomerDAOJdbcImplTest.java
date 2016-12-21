package com.cqg.mvcapp.test;

import java.util.List;

import org.junit.Test;

import com.cqg.mvcapp.dao.CriteriaCustomer;
import com.cqg.mvcapp.dao.CustomerDAO;
import com.cqg.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import com.cqg.mvcapp.domain.Customer;

public class CustomerDAOJdbcImplTest {
	
	private CustomerDAO customerDAO=new CustomerDAOJdbcImpl();
	
	@Test
	public void testgetForCriteriaCustomer(){
		CriteriaCustomer criteriaCustomer=new CriteriaCustomer("T", null, null);
		List<Customer> customers=customerDAO.getForCriteriaCustomer(criteriaCustomer);
		System.out.println(customers);
	}
	@Test
	public void testGetAll() {
		System.out.println(customerDAO.getAll());
	}

	@Test
	public void testSave() {
		Customer customer=new Customer();
		customer.setId(1);
		customer.setName("Carry");
		customer.setAddress("Beijing");
		customer.setPhone("1393900592");
		customerDAO.save(customer);
	}

	@Test
	public void testGetInteger() {
		System.out.println(customerDAO.get(1));
	}

	@Test
	public void testDelete() {
		customerDAO.delete(1);
	}

	@Test
	public void testGetCountWithName() {
		System.out.println(customerDAO.getCountWithName("Joker"));
	}

}
