import { React,Component} from 'react';
import EmployeeDetailsAPI from '../../Services/EmployeeDetailServices/EmployeeDetailsAPI'

// Retrive single employee data for update in the system
class EmployeeDetails extends Component 
{
  constructor(props){
    super(props)
    this.state = {
      employees:[]   
    }
      this.searchHandler= this.searchHandler.bind(this);
      this.retriveEmlployeeDetails = this.retriveEmlployeeDetails.bind(this);
  }    
    componentDidMount() {      
      this.retriveEmlployeeDetails();
      console.log(this.state)
      }
      
      // fetch the record from the database
      retriveEmlployeeDetails()
      {
        EmployeeDetailsAPI.retrieveEmployeeDetails()
        .then(response => {
          const employees = response.data;
          this.setState({ employees})
        })
      }

        // Delete method
      deleteEmployee(id){        
        EmployeeDetailsAPI.deleteEmployeeDetail(id)
          .then(Response => {      
            this.setState({employees: this.state.employees.filter(
              employee => employee.id !== id
            )});             
          });
    }

    //Update the existant record
    editEmployee(id){
      this.props.history.push(`/updateEmployeeDetail/${id}`);
  }        

  // serch record by id
  searchHandler = (event) => {
    this.setState({search_id: event.target.value})
}

  // search data base on id
  searchEmployee = () => {    
    this.props.history.push(`/searchEmployeeDetail/${this.state.search_id}`)                
}
      render() {        
        return (           
            <div> 
               <br></br>                 
                 <div className = "container col-md-4" >
                      <div className = "row" >
                          <div className = "col-md-8 offset-md-8 offset-md-0">                             
                              <div>
                                  <form>
                                      <div >
                                      <table>
                                        <thead>
                                            <tr>
                                                <th> Id Number </th>
                                                <th>  <input placeholder="Search " style={{marginLeft: "10px"}} name="search_id" className="form-control" 
                                                      value={this.state.search_id} onChange={this.searchHandler}/></th>
                                                <th>  <button style={{marginLeft: "15px"}} className="btn btn-success" onClick={this.searchEmployee}>Search</button></th>                     
                                            </tr>
                                        </thead>
                                        </table>                                                                              
                                      </div> 
                                  </form>
                              </div>
                          </div>
                      </div>
                 </div>  
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
                  {
                      this.state.employees.map(
                          employee =>              
                          <tr key = {employee.id} className = "table table-striped table-bordered">
                                <td> {employee.id} </td> 
                               <td> {employee.first_name} </td>   
                               <td> {employee.last_name}</td>
                               <td> {employee.email}</td>                                              
                               <td>
                                   <button onClick={ () => this.editEmployee(employee.id)} className="btn btn-info">Update </button>
                                   <button style={{marginLeft: "10px"}} onClick={ () => this.deleteEmployee(employee.id)} className="btn btn-danger">Delete </button>
                               </td>
                          </tr>
                      )
                  }
              </tbody>
            </table>

            </div>
         </div>
            </div>
           
        )
        
      }
      
    }     

export default EmployeeDetails;