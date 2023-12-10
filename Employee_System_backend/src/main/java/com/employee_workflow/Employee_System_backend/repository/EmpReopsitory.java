package com.employee_workflow.Employee_System_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_workflow.Employee_System_backend.model.Emp;
public interface EmpReopsitory extends JpaRepository<Emp, Long> {
    List<Emp> findByStatus(String status);
    Optional<Emp> findByEmpId(Long empId);
	void deleteByEmpId(Long empId);
}
