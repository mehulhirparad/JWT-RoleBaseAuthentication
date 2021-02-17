import React,{Component}from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Landing from '.././Components/Landing/Landing';
import SignUp from '.././Components/SignUp/SignUp';
import DesktopRouter from './DesktopRouter';

// Langind router component it is use after landing the frotpage of application
class LandingRouter extends Component {
    render() {
        return (
            <div className="TodoApp">
                <Router>
                    <>
                        <Switch>                           
                            <Route exact path="/" component={Landing}/>
                            <Route path="/signUp" component={SignUp}/>
                            <Route path="/desktopRouter" component={DesktopRouter}/>                                                                                                                               
                        </Switch>
                    </>
                </Router>               
            </div>
        )
    }
}

export default LandingRouter