package com.employee_workflow.Employee_System_backend.service;

import java.util.List;

import com.employee_workflow.Employee_System_backend.model.TaskAssign;

public interface TaskService {
	TaskAssign assignTask (TaskAssign task);
	List<TaskAssign> getAllTasks();
	String getTaskStatus(Long taskId);
	List<TaskAssign> getAssignedTasksByEmpId(Long empId);

}