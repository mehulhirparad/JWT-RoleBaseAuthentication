import { React,Component} from 'react';
import axios from 'axios'
import EmployeeDetailsAPI from '../../Services/EmployeeDetailServices/EmployeeDetailsAPI';


// Searchdata functionality
class SearchEmployeeDetail extends Component 
{
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            first_name: '',
            last_name: '',
            emailId: '',
        }     
        this.retriveEmployee = this.retriveEmployee.bind(this);
  }
    
  componentDidMount()
  {
    this.retriveEmployee();
    console.log(this.state)    
  }       

          // Retrive single object as per key value
          retriveEmployee()
          {
            EmployeeDetailsAPI.retriveEmployeeDetailById(this.state.id)
            .then( (response) =>{
              let employee = response.data;
              this.setState({first_name: employee.first_name,
                  last_name: employee.last_name,
                  email : employee.email
              });
          });
          }

        // Delete method 
      deleteEmployee(id){
        EmployeeDetailsAPI.deleteEmployeeDetail(id)          
          .then(Response => {      
            // this.setState({employees: this.state.employees.filter(
            //   employee => employee.id !== id
            // )});  
            this.props.history.push(`/employeeDetails`);           
          });
    }

    //Update the existant record
    editEmployee(id){
      this.props.history.push(`/updateEmployeeDetail/${id}`);
  }        

  // serch record by id
  searchHandler = (event) => {
    this.setState({search_id: event.target.value})
    console.log(this.state.search_id)
}

  // search data base on id
  searchEmployee = (e) => {
    e.preventDefault();
                
                  // Call RestAPI base on id number
                  EmployeeDetailsAPI.retriveEmployeeDetailById(this.state.search_id)
                 // axios.get('http://localhost:8088/api/employees/'+this.state.search_id)
                  .then(Response => {
                    const employees = Response.data;                        
                    this.setState({ employees});
                  })        
}
      render() {        
        return (           
            <div>               
            <br></br>
            <div className = "container col-md-6">
          <div className = "row center">
          <table className = "table table-striped table-bordered">
              <thead>
                  <tr>
                      <th> Id Number</th>
                      <th> First Name</th>
                      <th> Last Name</th>
                      <th> Email</th>
                      <th> Actions</th>
                  </tr>
              </thead>
              <tbody className = "table table-striped table-bordered">
              <tr key = {this.state.id} className = "table table-striped table-bordered">
                                <td> {this.state.id}</td>
                                <td> {this.state.first_name} </td> 
                               <td> {this.state.last_name} </td>   
                               <td> {this.state.email}</td>
                                                                             
                               <td>
                                <button onClick={ () => this.editEmployee(this.state.id)} className="btn btn-info">Update </button>
                                   <button style={{marginLeft: "10px"}} onClick={ () => this.deleteEmployee(this.state.id)} className="btn btn-danger">Delete </button> 
                               </td>
                          </tr>
              </tbody>
            </table>

            </div>
         </div>
            </div>
           
        )
        
      }
      
    }     
export default SearchEmployeeDetail;