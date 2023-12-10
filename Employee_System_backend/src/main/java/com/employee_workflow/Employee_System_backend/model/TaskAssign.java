package com.employee_workflow.Employee_System_backend.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "tasks")
public class TaskAssign {

	@Id
	@GeneratedValue(generator = "userSequenceGenerator")
	private Long id;
	
	private Long empId;
	private String task;
	private String description;
    private Date startDate;
    private Date endDate;
    private String clientName;

    
    
}
