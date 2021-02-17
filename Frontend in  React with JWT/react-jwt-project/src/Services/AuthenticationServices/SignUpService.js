import axios from 'axios';
import { API_URL } from '../../Constant/URLConstant';

// Signup service call
class SignUpService
{  

     // call for new Registration of USer
     userRegistration(userRegister)
     {
         return axios.post(`${API_URL}/register`,userRegister,
         {
            "headers": {
              "content-type": "application/json"                          
         }
        });
     }
    
}
export default new SignUpService();