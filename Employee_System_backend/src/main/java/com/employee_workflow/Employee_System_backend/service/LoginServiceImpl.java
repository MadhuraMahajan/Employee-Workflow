package com.employee_workflow.Employee_System_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_workflow.Employee_System_backend.model.Login;
import com.employee_workflow.Employee_System_backend.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login authenticateUser(String username, String password) {
        return loginRepository.findByUsernameAndPassword(username, password);
    }
}