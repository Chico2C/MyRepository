package com.cqg.sssp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqg.sssp.Repository.EmployeeRepository;
import com.cqg.sssp.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional(readOnly=true)
	public Employee getById(Integer id){
		return employeeRepository.findOne(id);
	}
	
	@Transactional
	public void delete(Integer id){
		employeeRepository.delete(id);
	}
	
	@Transactional
	public void save(Employee employee){
		if(employee.getId()==null){
			employee.setCreateTime(new Date());
		}
		employeeRepository.saveAndFlush(employee);
	}
	
	@Transactional(readOnly=true)
	public Employee getByLastName(String lastName){
		return employeeRepository.getByLastName(lastName);
	}
	
	@Transactional(readOnly=true)
	public Page<Employee> getPage(int pageNo,int pageSize){
		PageRequest pageable=new PageRequest(pageNo-1, pageSize);
		Page<Employee> page=employeeRepository.findAll(pageable);
		return page;
	}
}
