package com.employee.mgt.dao;

import java.util.List;

import com.employee.mgt.entitys.Employee;


public interface EmployeeDAO
{
	public List<Employee> findAll() ;

	public Employee save(Employee theEmployee);

	public Employee getById(int id);

	public void deleteById(int id);

	public void updateId(int id, Employee theEmployee);
}
