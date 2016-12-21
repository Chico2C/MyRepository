package com.cqg.mvcapp.dao.impl;

import java.util.List;

import com.cqg.mvcapp.dao.CriteriaCustomer;
import com.cqg.mvcapp.dao.CustomerDAO;
import com.cqg.mvcapp.dao.DAO;
import com.cqg.mvcapp.domain.Customer;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO{

	@Override
	public List<Customer> getAll() {
		String sql="select * from customers";
		return getForList(sql);
	}

	@Override
	public void save(Customer customer) {
		String sql="insert into customers(id,name,address,phone) values(?,?,?,?)";
		update(sql, customer.getId(),customer.getName(),customer.getAddress(),customer.getPhone());
	}

	@Override
	public Customer get(Integer id) {
		String sql="select * from customers where id=?";
		return get(sql, id);
	}

	@Override
	public void delete(Integer id) {
		String sql="delete from customers where id=?";
		update(sql, id);
		
	}

	@Override
	public long getCountWithName(String name) {
		String sql="select count(id) from customers where name=?";
		
		return getForValue(sql, name);
	}

	@Override
	public List<Customer> getForCriteriaCustomer(CriteriaCustomer cc) {
		String sql="select * from customers where name like ? and address like ?"
				+ "and phone like ?";
		return getForList(sql,cc.getName(),cc.getAddress(),cc.getPhone());
	}

	@Override
	public void update(Customer customer) {
		String sql="update customers set name=?,address=? ,phone=? where id=?";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone(),customer.getId());
		
	}

}
