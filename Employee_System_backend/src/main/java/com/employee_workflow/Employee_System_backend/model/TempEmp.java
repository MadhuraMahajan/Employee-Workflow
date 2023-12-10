package com.employee_workflow.Employee_System_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "temp_emp")
public class TempEmp {

    @Id
    @GeneratedValue(generator = "userSequenceGenerator")
    private Long id;

    private String empname;
    private String email;
    private String address;
    private String phoneNo;
    private int empId;
    private String operation;
    private String password;
    private String status;
    private boolean approved = true;
}
