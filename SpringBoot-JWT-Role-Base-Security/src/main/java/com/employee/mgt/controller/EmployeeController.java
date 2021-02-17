package com.employee.mgt.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.mgt.entitys.Employee;
import com.employee.mgt.services.EmployeeServices;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
@ComponentScan("Com.spring.RESTAPI")
public class EmployeeController 
{	
	private EmployeeServices employeeServices;		
	
	@Autowired
	public EmployeeController(EmployeeServices employeeServices)
	{
		this.employeeServices = employeeServices;		
	}	
	
	// Get list of employees from the data base
	//@GetMapping("/employees123")
	 @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	 @ResponseBody
	public ResponseEntity<List<Employee>> findAll()
	{		
		List<Employee> list = employeeServices.findAll();				 
		return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
	}		
	 
	// Get single employee detail base on id
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	 @ResponseBody
	public ResponseEntity<Employee> getById(@PathVariable int id)
	{
		System.out.println(""+id );	
		Employee linkEmployee = employeeServices.getById(id);
		return new ResponseEntity<Employee>(linkEmployee,HttpStatus.OK);
		
	}
	// Post detail of employee in the database
		@RequestMapping(value="/employee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
		@ResponseBody
		public ResponseEntity<?> save(@Valid @RequestBody Employee theEmployee, BindingResult result)
		{
			if(result.hasErrors())
			{
				Map<String, String> errorMap = new HashMap<String, String>();
				for(FieldError error:result.getFieldErrors())
				{
					System.out.println("error pass has been executed ");
					errorMap.put(error.getField(),error.getDefaultMessage());
				}
				return new ResponseEntity<Map>(errorMap, HttpStatus.OK);				
			}
			System.out.println("Post method has been called");
			Employee employee = employeeServices.save(theEmployee);
			return new ResponseEntity<>(null,HttpStatus.OK);
		}		
	
	// Delete the employee detail form the database
	@DeleteMapping("/employee/{id}")
	public void deleteById(@PathVariable int id)
	{
		System.out.println("Delete method has been called");
		employeeServices.deleteById(id);
	}
	
	// Update the employee user
	@PutMapping("/employee/{id}")
	public Employee updateId(@PathVariable int id,@RequestBody Employee theEmployeeDetail)
	{
		Employee theEmployee = employeeServices.getById(id);
		theEmployee.setFirst_name(theEmployeeDetail.getFirst_name());
		theEmployee.setLast_name(theEmployeeDetail.getLast_name());
		theEmployee.setEmail(theEmployeeDetail.getEmail());
		employeeServices.updateId(id, theEmployeeDetail);				
		return theEmployee;
	}	
}
