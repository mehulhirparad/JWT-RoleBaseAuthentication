import axios from 'axios';
import React, { Component } from 'react';
import SignUpService from '../../Services/AuthenticationServices/SignUpService';

// SignUp form class show error with data 
class SignUp extends Component {
  
  constructor(props){
    super(props)

    this.state = {
        userName:'',
        password:'',
        matchingPassword:'',
        first_name:'',
        last_name:'',
        email:'',
        mobile:'',
        message:'',
        userName_error:null,
        password_error:null,
        matchingPassword_error:null,
        first_name_error:null,
        last_name_error:null,
        email_error:null,
        mobile_error:null
    }
        this.changeUserNameHandler = this.changeUserNameHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.changeMatchingPasswordHandler = this.changeMatchingPasswordHandler.bind(this);
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changeMobileHandler = this.changeMobileHandler.bind(this);
    }

    change = (e) => {
        this.setState({[e.target.name]:e.target.value})
    }

    submitHandler = e => {
        e.preventDefault()
        console.log(this.state)      
        .then(response => {
            console.log(response.data)                
        })
        .catch(details =>{
            console.log(details)                
        }) 
    }
    // UserName change handler
    changeUserNameHandler = (event) => {
      this.setState({userName: event.target.value})     
  }

  // Passsword change handler
    changePasswordHandler = (event) => {
      this.setState({password: event.target.value})      
    }
  // Matching password change handler
  changeMatchingPasswordHandler = (event) => {
    this.setState({matchingPassword: event.target.value})   
  }
    // Name change handler
    changeFirstNameHandler = (event) => {
        this.setState({first_name: event.target.value})      
    }

    // Last name change handler
    changeLastNameHandler = (event) => {
        this.setState({last_name: event.target.value})
    }

    // Email change handler
    changeEmailHandler = (event) => {
        this.setState({email: event.target.value})
    } 

    // Passsword change handler
  changeMobileHandler = (event) => {
    this.setState({mobile: event.target.value})    
  }

    // Save the data method call
    saveEmployee = (e) => {
        e.preventDefault();
        let userRegister = JSON.stringify({
          "userName":this.state.userName,
          "password":this.state.password,
          "matchingPassword":this.state.matchingPassword,
          "first_name": this.state.first_name,
          "last_name": this.state.last_name,
          "email":this.state.email,
          "mobile":this.state.mobile
                    })                  
                    // axios.post('http://localhost:8088/register',userRegister,{
                    //     "headers": {
                    //       "content-type": "application/json"                          
                    //       }
                    //   })
                    SignUpService.userRegistration(userRegister)
                      .then(response => {                       
                          if(!response.data)
                          {
                              console.log("resonse is null")  
                              this.props.history.push('/');
                          }
                          else{   
                               console.log(response)
                                this.setState({first_name_error: response.data.first_name});                      
                                this.setState({last_name_error: response.data.last_name}); 
                                this.setState({userName_error: response.data.userName});                      
                                this.setState({password_error: response.data.password}); 
                                this.setState({email_error: response.data.email});                      
                                this.setState({mobile_error: response.data.mobile});                            
                                this.setState({message: response.data.message});              
                              }                                         
                    })         
                    .catch(error => {
                      console.log("there is some error in code");                    
                      console.log(error.response);
                    })                   
    }

    // cancel button 
    cancelEmployee = (e) => {
      this.props.history.push('/');      
    }

 render(){
      return(
        <div>
            <br></br>
               <div className = "container col-md-6">
                    <div className = "row" >
                        <div className = "card col-md-6 offset-md-14 offset-md-0">
                            <h3 className="text-center">Registration Form</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                    <label> {this.state.message} </label>
                                        <label> {this.state.userName_error} </label>                                       
                                        <input placeholder="UserName" name="userName" className="form-control" 
                                            value={this.state.userName} onChange={this.changeUserNameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                    <label> {this.state.password_error} </label>                                      
                                        <input input type="password" placeholder="Password" name="password" className="form-control" 
                                            value={this.state.password} onChange={this.changePasswordHandler}/>
                                    </div>
                                    <div className = "form-group">    
                                    <label> {this.state.matchingPassword_error} </label>                                  
                                        <input input type="password" placeholder="Confirm Password" name="matchingPassword" className="form-control" 
                                            value={this.state.matchingPassword} onChange={this.changeMatchingPasswordHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> {this.state.first_name_error} </label>                                       
                                        <input placeholder="First Name" name="firstName" className="form-control" 
                                            value={this.state.first_name} onChange={this.changeFirstNameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                    <label> {this.state.last_name_error} </label>                                      
                                        <input placeholder="Last Name" name="lastName" className="form-control" 
                                            value={this.state.last_name} onChange={this.changeLastNameHandler}/>
                                    </div>
                                    <div className = "form-group">   
                                    <label> {this.state.email_error} </label>                                   
                                        <input placeholder="Email Address" name="emailId" className="form-control"     
                                        value={this.state.email} onChange={this.changeEmailHandler}/>
                                    </div>
                                    <div className = "form-group">   
                                    <label> {this.state.mobile_error} </label>                                   
                                        <input placeholder="Mobile Number" name="mobile" className="form-control" 
                                            value={this.state.mobile} onChange={this.changeMobileHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveEmployee}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancelEmployee} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
               </div>
        </div>
      )
   }      
}
     



export default SignUp;
