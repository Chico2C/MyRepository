package com.cqg.mvcapp.dao.impl;

import java.util.List;

import com.cqg.mvcapp.dao.CriteriaCustomer;
import com.cqg.mvcapp.dao.CustomerDAO;
import com.cqg.mvcapp.domain.Customer;

public class CustomerDAOXMLImpl implements CustomerDAO{

	@Override
	public List<Customer> getForCriteriaCustomer(CriteriaCustomer cc) {
		System.out.println("getForCriteriaCustomer");
		return null;
	}

	@Override
	public List<Customer> getAll() {
		System.out.println("getAll");
		return null;
	}

	@Override
	public void save(Customer customer) {
		System.out.println("save");
	}

	@Override
	public Customer get(Integer id) {
		System.out.println("get");
		return null;
	}

	@Override
	public void delete(Integer id) {
		System.out.println("delete");
		
	}

	@Override
	public void update(Customer customer) {
		System.out.println("update");
		
	}

	@Override
	public long getCountWithName(String name) {
		System.out.println("getCountWithName");
		return 0;
	}

}
