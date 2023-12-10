package com.employee_workflow.Employee_System_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "emp")

public class Emp {

    @Id
    @GeneratedValue(generator = "userSequenceGenerator")

    private Long id;

    private String empname;
    private String email;
    private String address;
    private String phoneNo;  // Change type to String to handle leading zeros
    private int empId;
    private String password;
    private String status;
    private String operation;
    private boolean approved = true;
}