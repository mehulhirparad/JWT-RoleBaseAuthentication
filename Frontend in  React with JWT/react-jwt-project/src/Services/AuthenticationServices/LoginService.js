import axios from 'axios';
import { API_URL } from '../../Constant/URLConstant';
export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
export const User_toke = 'token'

// Login Service API class 
class LoginService
{
    // call for Authentication varification only
    getAccessJwtAuthenticationService(username, password) {
        return axios.post(`${API_URL}/authenticate`, {
            username,
            password
    
        })
    }
    
}
export default new LoginService();