package com.employee.mgt.entitys;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name="employee")
public class Employee extends RepresentationModel<Employee>
{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
		//@Size(min = 2, message = "Name should have atleast 2 characters")
		@Column(name="first_name")
		private String first_name;
		
		//@Size(min = 2, message = "Last should have atleast 2 characters")
		@Column(name="last_name")
		private String last_name;
		
		@Column(name="email")
		private String email;		
		
		public Employee() {
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Employee(String first_name, String last_name, String email){
			super();
			this.first_name = first_name;
			this.last_name = last_name;
			this.email = email;
		}
		@Override
		public String toString() {
			return "Employree [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
					+ email + "]";
		}	
		
		
}
