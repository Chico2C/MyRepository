package com.cqg.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.cqg.ssh.entities.Employee;
import com.cqg.ssh.service.DepartmentService;
import com.cqg.ssh.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction extends ActionSupport implements RequestAware,ModelDriven<Employee>,
             Preparable{
 
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService;
    private DepartmentService departmentService;
    
    public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
    public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
    public String delete(){
    	try {
			employeeService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
    	return "ajax-valid";
    }
	
	public String list(){
		List<Employee> emps=employeeService.getAll();
		request.put("employees", emps);
		return "list";
	}
	
	public String input(){
		request.put("departments", departmentService.getAll());
		
		return "input";
	}
	
	public void prepareInput(){
		if(id !=null){
			model=employeeService.get(id);
		}
	}
	
	public String save(){
		if(id==null){
			model.setCreateTime(new Date());
		}
		employeeService.saveOrUpdate(model);
		return "save";
	}
	
	public void prepareSave(){
		if(id==null){
			model=new Employee();
		}else{
			model=employeeService.get(id);
		}
	}
	
	public String validLastName(){
		if(employeeService.getEmployeeByLastName(lastName)){
			try {
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return "ajax-valid";
	}
	
	private InputStream inputStream;
   
	public InputStream getInputStream() {
        return inputStream;
    }
	
	private String lastName;
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
    private Map<String , Object > request;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}

	@Override
	public void prepare() throws Exception {}

	private Employee model;
	
	@Override
	public Employee getModel() {
		return model;
	}
	
}
