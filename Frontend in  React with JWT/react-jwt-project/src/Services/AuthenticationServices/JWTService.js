import { Component } from "react";
import axios from 'axios';
export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
export const USER_TOKEN_SESSION_ATTRIBUTE_NAME = 'token'

// JWT configuration class which is responsible for sessionStorege for applicaiton 
// JWT configuration provide axiosinterceptor method which is manage header of all 
// request send to the server side 
class JWTService extends Component
{  
    registerSuccessfulLogin(username, password) {        
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        this.setupAxiosInterceptors(this.createBasicAuthToken(username, password))
    }

    loginForJwt(username, token) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        sessionStorage.setItem(USER_TOKEN_SESSION_ATTRIBUTE_NAME,token)
        this.setupAxiosInterceptors(this.createJWTToken(token))
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }

    creteToken()
    {
        let jwtToken = sessionStorage.getItem(USER_TOKEN_SESSION_ATTRIBUTE_NAME)
        return 'Bearer ' + jwtToken
    }

    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem(USER_TOKEN_SESSION_ATTRIBUTE_NAME);
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) 
        {
            console.log(user)
            return false
        }else{
            console.log(user)
            return true
        }     
        
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }

    setupAxiosInterceptors(token) {

        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                console.log("config method has been executed")
                return config
            }
        )
    }
}

export default new JWTService();