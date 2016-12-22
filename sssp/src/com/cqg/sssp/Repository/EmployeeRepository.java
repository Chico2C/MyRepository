package com.cqg.sssp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqg.sssp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Employee getByLastName(String lastName);	
	
}
