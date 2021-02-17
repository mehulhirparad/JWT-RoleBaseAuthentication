package com.employee.mgt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.mgt.dao.EmployeeDAO;
import com.employee.mgt.entitys.Employee;

@Service
public class EmployeeServicesImp implements EmployeeServices
{
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServicesImp(EmployeeDAO employeeDAO)
	{
		this.employeeDAO = employeeDAO;
	}
	@Override
	public List<Employee> findAll() {
		
		System.out.println("2");
		return employeeDAO.findAll();
	}
	@Override
	public Employee save(Employee theEmployee) {
		
		return employeeDAO.save(theEmployee);
		
	}
	@Override
	public Employee getById(int id) {
		
		return employeeDAO.getById(id);
	}
	@Override
	public void deleteById(int id) {
		employeeDAO.deleteById(id);
	}
	
	@Override
	public void updateId(int id, Employee theEmployee)
	{
		employeeDAO.updateId(id, theEmployee);		
	}

}
