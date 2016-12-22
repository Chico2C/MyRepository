package com.cqg.ssh.service;

import java.util.List;

import com.cqg.ssh.dao.EmployeeDao;
import com.cqg.ssh.entities.Employee;

public class EmployeeService {
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
    
	public List<Employee> getAll(){
		return employeeDao.getAll();
	}
	
	public void delete(Integer id){
		employeeDao.delete(id);
	}
	
	public void saveOrUpdate(Employee employee){
		employeeDao.saveOrUpdate(employee);
	}
	
	public Boolean getEmployeeByLastName(String lastName){
		return employeeDao.getEmployeeByLastName(lastName)==null;
	}

	public Employee get(Integer id) {
		
		return employeeDao.get(id);
	}
}
