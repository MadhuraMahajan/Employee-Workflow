package com.employee_workflow.Employee_System_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_workflow.Employee_System_backend.model.TaskAssign;
import com.employee_workflow.Employee_System_backend.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskAssign assignTask(TaskAssign task) {
        return taskRepository.save(task);
    }

    @Override
    public List<TaskAssign> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskAssign> getAssignedTasksByEmpId(Long empId) {
    	List<TaskAssign> tasks= taskRepository.findByEmpId(empId);
    	System.out.println(tasks);
    	return tasks;
    }

	@Override
	public String getTaskStatus(Long taskId) {
		// TODO Auto-generated method stub
		return null;
	}
}
