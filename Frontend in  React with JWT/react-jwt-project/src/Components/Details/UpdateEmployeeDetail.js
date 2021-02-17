import React from 'react';
import axios from 'axios';
import { Component } from 'react';
import EmployeeDetailsAPI from '../../Services/EmployeeDetailServices/EmployeeDetailsAPI'

// Updata the employee Object
class updateEmployeeDetail extends Component
{
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            first_name: '',
            last_name: '',
            emailId: ''
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.UpdateEmployee = this.UpdateEmployee.bind(this);
        this.updateData = this.updateData.bind(this);
    }

    // using this method first we get the data form the database and 
    // then we edit as per our requirement
    componentDidMount()
    {
        this.updateData();
       console.log(this.state)
    }      
    
    //Initialise data to varialbe after getting form database
    updateData()
    {
    // call 
        EmployeeDetailsAPI.retriveEmployeeDetailById(this.state.id)
        .then(response => {
             let employee = response.data;
             this.setState({first_name: employee.first_name,
                             last_name: employee.last_name,
                             email : employee.email
                 });
             });
    }

        
    // After editting data make update
    UpdateEmployee = (e) => {
        e.preventDefault();
        let employee = {first_name: this.state.first_name, last_name: this.state.last_name, email: this.state.email};
        console.log('employee => ' + JSON.stringify(employee));

        // call Rest Api using parameter like id and employee
        EmployeeDetailsAPI.putEmployeeDetail(this.state.id,employee)  
         .then(response => {
            console.log(response);
            this.props.history.push('/employeeDetails');
        })  ;     
        }


    // First Name change hadnler method
    changeFirstNameHandler= (event) => {
        this.setState({first_name: event.target.value});
    }

    // Last name change handler method
    changeLastNameHandler= (event) => {
        this.setState({last_name: event.target.value});
    }

    // Email change hadler method
    changeEmailHandler= (event) => {
        this.setState({email: event.target.value});
    }

    cancelHandler = (e) => {
        this.props.history.push('/employeeDetails');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">                                            
                                            <input placeholder="First Name" name="firstName" className="form-control" 
                                                value={this.state.first_name} onChange={this.changeFirstNameHandler}/>
                                        </div>
                                        <div className = "form-group">                                           
                                            <input placeholder="Last Name" name="lastName" className="form-control" 
                                                value={this.state.last_name} onChange={this.changeLastNameHandler}/>
                                        </div>
                                        <div className = "form-group">                                           
                                            <input placeholder="Email Address" name="emailId" className="form-control" 
                                                value={this.state.email} onChange={this.changeEmailHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.UpdateEmployee}>Save</button>
                                        <button className="btn btn-danger"  onClick={this.cancelHandler} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }


}

export default updateEmployeeDetail;