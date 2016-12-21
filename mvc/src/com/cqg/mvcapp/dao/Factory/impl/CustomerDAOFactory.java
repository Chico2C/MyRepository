package com.cqg.mvcapp.dao.Factory.impl;

import java.util.HashMap;
import java.util.Map;

import com.cqg.mvcapp.dao.CustomerDAO;
import com.cqg.mvcapp.dao.impl.CustomerDAOJdbcImpl;
import com.cqg.mvcapp.dao.impl.CustomerDAOXMLImpl;


public  class CustomerDAOFactory {
    private static CustomerDAOFactory instance=new CustomerDAOFactory();
    
    Map<String, CustomerDAO> daos=new HashMap<>();
    
    public static CustomerDAOFactory getInstance(){
    	return instance;
    }
    
    private String type=null;
	
    public void setType(String type){
    	this.type=type;
    }
    
	private CustomerDAOFactory(){
    	daos.put("jdbc", new CustomerDAOJdbcImpl());
    	daos.put("xml", new CustomerDAOXMLImpl());	
    }
	
	public CustomerDAO getType(){
		return daos.get(type);
	}
}
