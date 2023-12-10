package com.employee_workflow.Employee_System_backend.service;

import com.employee_workflow.Employee_System_backend.model.Login;

public interface LoginService {
    Login authenticateUser(String username, String password);
}
