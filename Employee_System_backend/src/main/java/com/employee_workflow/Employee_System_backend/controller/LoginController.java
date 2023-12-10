package com.employee_workflow.Employee_System_backend.controller;
//LoginController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee_workflow.Employee_System_backend.dto.LoginRequest;
import com.employee_workflow.Employee_System_backend.model.Login;
import com.employee_workflow.Employee_System_backend.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

 @Autowired
 private LoginService loginService;

 @PostMapping("/login")
 public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
     String username = loginRequest.getUsername();
     String password = loginRequest.getPassword();

     Login login = loginService.authenticateUser(username, password);

     if (login != null) {
         String role = login.getRole();
         switch (role) {
             case "admin":
                 return ResponseEntity.ok("/Admin");
             case "hr":
                 return ResponseEntity.ok("/hr/Hrdelete");
             case "manager":
                 return ResponseEntity.ok("/AddEmp");
             case "employee":
                 return ResponseEntity.ok("/emp/empDashboard");
             default:
                 return ResponseEntity.ok("/dashboard"); // Default redirection
         }
     } else {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
     }
 }
}