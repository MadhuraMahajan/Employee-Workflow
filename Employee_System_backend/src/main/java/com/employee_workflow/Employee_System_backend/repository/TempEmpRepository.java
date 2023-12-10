package com.employee_workflow.Employee_System_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_workflow.Employee_System_backend.model.TempEmp;

public interface TempEmpRepository extends JpaRepository<TempEmp, Long> {
    List<TempEmp> findByStatus(String status);
    void deleteByEmpId(Long empId);
    Optional<TempEmp> findByEmpId(Long empId);


}
