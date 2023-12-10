package com.employee_workflow.Employee_System_backend.service;

import java.util.List;
import java.util.Optional;

import com.employee_workflow.Employee_System_backend.model.Emp;
import com.employee_workflow.Employee_System_backend.model.TempEmp;

import jakarta.transaction.Transactional;

public interface EmpService {
    List<Emp> getAllEmployees();
//3 15 4 5 2 25 8 1 18 19 8 1 12 
    Optional<Emp> getEmployeeById(Long empId);

    Emp saveEmployee(Emp employee);
     TempEmp saveTempEmployee(TempEmp emp);
    void deleteEmployee(Long empId);


    void approveTempRequest(Long empId, String approveString);

    void denyTempRequest(Long empId);

    List<Emp> getPendingRequests();


    void removeRequest(Long empId);
    public void denyTempRequestById(Long empId) ;
    List<TempEmp> getPendingTempRequests();

	void denyRequest(Long empId);
	 TempEmp submitUpdateForApproval(Emp emp, String operation);
	TempEmp submitForApproval(Emp emp, String operation);
	@Transactional
  public void submitDeleteApproval(Long empId);
}