package com.employee_workflow.Employee_System_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class NotificationRequest {
	   @Id
	   @GeneratedValue(generator = "userSequenceGenerator")
    private String employeeId;
    private String operation;

    // getters and setters

    // constructor
}