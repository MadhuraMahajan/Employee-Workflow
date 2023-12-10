package com.employee_workflow.Employee_System_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee_workflow.Employee_System_backend.model.TaskAssign;
@Repository
public interface TaskRepository extends JpaRepository<TaskAssign, Long>{

	List<TaskAssign> findByEmpId(Long empId);

}
