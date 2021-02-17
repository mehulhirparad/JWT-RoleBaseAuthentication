import React,{Component}from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import Navbar from '.././Components/Navigation/Navbar'
import EmployeeDetails from '../Components/Details/EmployeeDetails';
import updateEmployeeDetail from '../Components/Details/UpdateEmployeeDetail';
import SearchEmployeeDetail from '../Components/Details/SearchEmployeeDetail';
import CreateEmployeeDetail from '../Components/Details/CreateEmployeeDetail';
import Home from '../Components/Details/Home';
import JWTService from '../Services/AuthenticationServices/JWTService';

// DesktopRouter component which is handle all path of component
class DesktopRouter extends Component {

    state = {
        visible: true
    };
  render()
  {         
        return (
            <div className="JWTApp">
                
                <Router>   
                { this.state.visible ? <Navbar /> : this.props.history.push('/') }       
                    <Switch>
                    <Route exact path='/home' component={Home} />        
                    <Route path='/employeeDetails' component={EmployeeDetails} />
                    <Route path='/createEmployeeDetail' component={CreateEmployeeDetail} />
                    <Route path='/updateEmployeeDetail/:id' component={updateEmployeeDetail} />
                    <Route path='/searchEmployeeDetail/:id' component={SearchEmployeeDetail} />  
                    </Switch>                         
                     </Router>  
                    <Link  to="#" className="logout-link" onClick={ ()=> {                   
                    JWTService.logout();                   
                    this.setState({visible: false});
                }}>
                    Logout
                </Link> 
                </div>            
        );      
    }
 }

export default DesktopRouter;
