import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import JWTService from '../../Services/AuthenticationServices/JWTService'
import LoginService from '../../Services/AuthenticationServices/LoginService'

// Landing page 
class Landing extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }       
        
        this.loginClicked = this.loginClicked.bind(this)
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.changeUserNameHandler = this.changeUserNameHandler.bind(this);
    }

    // username change handler
    changeUserNameHandler = (event) => {
        this.setState({username: event.target.value})   
      }

    // password change handler
    changePasswordHandler = (event) => {
        this.setState({password: event.target.value})   
      }

    loginClicked() {
       
            LoginService.getAccessJwtAuthenticationService(this.state.username, this.state.password)
            .then((response) => {
                console.log("response done")
                JWTService.loginForJwt(this.state.username, response.data.token)
                console.log(response)
               this.props.history.push('/desktopRouter')              
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasLoginFailed: true })
            })
    }

    render() {
        return (
            <div>
                <h1>Login</h1>
                <div className="container col-md-3">
                    {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
                    {this.state.hasLoginFailed && <div className="alert alert-warning">Please Enter valid User and Password</div>}
                    {this.state.showSuccessMessage && <div>Login Sucessful</div>}
                    {/*<ShowLoginSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}
                    <div className = "form-group">                                   
                                User Name :- <input placeholder="UserName" name="userName" 
                                            value={this.state.username} onChange={this.changeUserNameHandler}/>
                                    </div>
                        <div className = "form-group">                                                                         
                                    Password :- <input type="password" placeholder="Password" name="password"
                                            value={this.state.password} onChange={this.changePasswordHandler}/>
                                    </div>                   
                    <button className="btn btn-success" onClick={this.loginClicked}>Login</button>
                </div>
                <Link className="signup-link" to="/signup">Sign Up</Link>
            </div>
        )
    }
}

export default Landing