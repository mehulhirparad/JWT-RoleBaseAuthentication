import axios from "axios";
import { API_URL } from "../../Constant/URLConstant";
export const USER_TOKEN_SESSION_ATTRIBUTE_NAME = 'token'

// RestApi for EmployeeDetail class which is implemented in spring boot 
class EmployeeDetailsAPI
{    
    // All EmployeeDetails class records retrive
    retrieveEmployeeDetails() {        
        let token = sessionStorage.getItem(USER_TOKEN_SESSION_ATTRIBUTE_NAME);
        // This testing code 
        return axios.get(`${API_URL}/api/employees`,          
         {
               config:{
                 headers: {                
                     'Authorization':token,   
                     'Access-Control-Allow-Origin' : '*' ,
                     'mode': 'cors',         
                     }
               }             
           }             
        );
     }       

     // Delete EmployeeDetail class row data
     deleteEmployeeDetail(id) {      
      let token = sessionStorage.getItem(USER_TOKEN_SESSION_ATTRIBUTE_NAME);     
      return axios.delete(`${API_URL}/api/employee/`+id,   
         {
             config:{
               headers: {                
                   'Authorization':token,   
                   'Access-Control-Allow-Origin' : '*' ,
                   'mode': 'cors',         
                   }
             }           
         }           
      );
   } 
   // Retrive single EmployeeDetail base on id
   // get the data as per id from the database
   retriveEmployeeDetailById(id){
    let token = sessionStorage.getItem(USER_TOKEN_SESSION_ATTRIBUTE_NAME);
    console.log("find by id"+id)
    return axios.get(`${API_URL}/api/employee/`+id,   
    {
        config:{
          headers: {                
              'Authorization':token,   
              'Access-Control-Allow-Origin' : '*' ,
              'mode': 'cors',         
                   }
                }      
            }      
        );
    }

    // get sngle data into the database
    putEmployeeDetail(id,employee){
        let token = sessionStorage.getItem(USER_TOKEN_SESSION_ATTRIBUTE_NAME);
        return  axios.put(`${API_URL}/api/employee/`+id,employee); 
    }

    //save single data into the database
    saveEmployeeDetail(empoyee){
        let token = sessionStorage.getItem(USER_TOKEN_SESSION_ATTRIBUTE_NAME);
        return axios.post(`${API_URL}/api/employee`, empoyee,   
        {
            config:{
              headers: {                
                  'Authorization':token,   
                  'Access-Control-Allow-Origin' : '*' ,
                  'mode': 'cors',         
                       }
                    }      
                }      
            );
        }
}
export default new EmployeeDetailsAPI();