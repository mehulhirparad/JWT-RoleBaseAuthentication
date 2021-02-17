# JWT-RoleBaseAuthentication
React + Springboot JWT authentication project

# Frontend (React)
   # Overview
   - Create login page with JWT Authentication token 
   - Create Signup page with user detail and store in database
   - Use SessionStorege for store the token and user name value which can be use for new request at the server
   - Create Logout function for remove sessionStorege
   - All function work base on REST API
   - Use AXIOS for making API call
   # AXIOS library which use in frontend project
        npm install axios :- use for rest api call from client side to server side.
        npm install react-icons --save :- if use to install icon for navigation bar.
        npm install --save react-router-dom :- use for routing for component in the project.
        
# Backend (Spring Boot)
  # Overview
  - Provide user login security.
  - Implement JWT Role base Authentication.
  - Create TOKEN and send to client.
  - Access the TOKEN from the client and find the rolebase authorities and API URL 
  - Implement Login and Logout page.
  - Create SignUp page for new user registration in the system (Which is always create USER role so modify as per your learn )
  - Design EMPLOYEE entity for performing some REST API operation like GET, DELETE, PET, UPDATE. 
  - Use validation.
  - Hibernate         

# Login Page 
![](Project%20Photos/LoginPage.jpg)

# Login Page with error message
![](Project%20Photos/LoginPageEror.jpg)

# Sign up Page with error message
![](Project%20Photos/SignUpPageWithError.jpg)

# Sign up Page with USER NAME ALREADY EXISTS message
![](Project%20Photos/NewUserRegistrationError.jpg)

# Panel
![](Project%20Photos/PanelPage.jpg)

# List of Employee
![](Project%20Photos/AllRecords.jpg)

# Serch base on user ID
![](Project%20Photos/SearchOption.jpg)


