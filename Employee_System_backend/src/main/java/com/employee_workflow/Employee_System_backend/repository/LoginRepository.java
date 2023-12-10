package com.employee_workflow.Employee_System_backend.repository;
//3 15 4 5 2 25 8 1 18 19 8 1 12 
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_workflow.Employee_System_backend.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByUsernameAndPassword(String username, String password);
}