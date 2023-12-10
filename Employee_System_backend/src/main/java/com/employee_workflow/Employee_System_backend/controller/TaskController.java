package com.employee_workflow.Employee_System_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_workflow.Employee_System_backend.model.TaskAssign;
import com.employee_workflow.Employee_System_backend.service.TaskService;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin("http://localhost:3000")
public class TaskController {

	private final TaskService taskService;
	
	@Autowired
	 public TaskController(TaskService taskService) {
	        this.taskService = taskService;
	    }
	
	@PostMapping("/assign")
	public ResponseEntity<TaskAssign> assignTask(@RequestBody TaskAssign task){
		TaskAssign assignedTask = taskService.assignTask(task);
		return new ResponseEntity<>(assignedTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<TaskAssign>> getAllTasks(){
		List<TaskAssign> tasks = taskService.getAllTasks();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	  @GetMapping("/byEmpId/{empId}")
	    public ResponseEntity<List<TaskAssign>> getTasksByEmpId(@PathVariable Long empId) {
	        List<TaskAssign> tasks = taskService.getAssignedTasksByEmpId(empId);
	        return new ResponseEntity<>(tasks, HttpStatus.OK);
	    }
}
