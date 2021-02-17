package com.employee.mgt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.employee.mgt.entitys.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO
{
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOImpl(EntityManager entityManager)
	{
		this.entityManager=entityManager;
	}
	@Override
	@Transactional
	public List<Employee> findAll() {
		
		  Session session= entityManager.unwrap(Session.class);
		  
		  Query<Employee> theQuery=
				  session.createQuery("from Employee", Employee.class);
		  
		 List<Employee> employees = theQuery.getResultList();
		 System.out.println("3");
		return employees;
	}
	@Override
	@Transactional
	public Employee save(Employee theEmployee) {
		
		Session session= entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(theEmployee);
				
		return theEmployee;			
	}
	@Override
	public Employee getById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = session.get(Employee.class, id);
		
		return employee;
	}
	@Override
	@Transactional
	public void deleteById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query theQuery = session.createQuery("delete from Employee where id=:id");
		
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();
	}
	
	@Override
	@Transactional
	public void updateId(int id, Employee theEmployee) {
		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = session.get(Employee.class, id);
		employee.setFirst_name(theEmployee.getFirst_name());
		employee.setLast_name(theEmployee.getLast_name());
		employee.setEmail(theEmployee.getEmail());			
		session.update(employee);
				
	}
}
