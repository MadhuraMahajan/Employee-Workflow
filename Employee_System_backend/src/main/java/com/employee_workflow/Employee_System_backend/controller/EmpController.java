package com.employee_workflow.Employee_System_backend.controller;
/// EmpController.java

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee_workflow.Employee_System_backend.model.Emp;
import com.employee_workflow.Employee_System_backend.model.NotificationRequest;
import com.employee_workflow.Employee_System_backend.model.TempEmp;
import com.employee_workflow.Employee_System_backend.service.EmpService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000") 
@CrossOrigin(origins = "*") // Adjust the origin based on your frontend URL
@RequestMapping("/api/emps")
public class EmpController {

	  private EmpService empService;

	    @Autowired
	    public void setEmpService(EmpService empService) {
	        this.empService = empService;
	    }
    @GetMapping
    public ResponseEntity<List<Emp>> getAllEmps() {
        List<Emp> emps = empService.getAllEmployees();
        return new ResponseEntity<>(emps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emp> getEmpById(@PathVariable Long id) {
        return empService.getEmployeeById(id)
                .map(emp -> new ResponseEntity<>(emp, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Emp> addEmp(@RequestBody Emp emp) {
        Emp savedEmp = empService.saveEmployee(emp);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emp> updateEmp(@PathVariable Long id, @RequestBody Emp emp) {
        if (!empService.getEmployeeById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        emp.setId(id);
        Emp updatedEmp = empService.saveEmployee(emp);
        return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable Long id) {
        if (!empService.getEmployeeById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        empService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  
    @PostMapping("/notifyAdmin")
    public ResponseEntity<Void> notifyAdmin(@RequestBody NotificationRequest request) {
        // ...

        return new ResponseEntity<>(HttpStatus.OK);
    }
    

//    @DeleteMapping("/removeRequest/{id}")
//    public ResponseEntity<Void> removeRequest(@PathVariable Long id) {
//        empService.removeRequest(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/pendingRequests")
//    public ResponseEntity<List<Emp>> getPendingRequests() {
//        List<Emp> pendingRequests = empService.getPendingRequests();
//        return new ResponseEntity<>(pendingRequests, HttpStatus.OK);
//    }
 // 3 15 4 5 2 25 8 1 18 19 8 1 12 .java


    @PostMapping("/submitForApproval")
    public ResponseEntity<Long> submitForApproval(@RequestBody Emp emp, @RequestParam String operation) {
        emp.setOperation(operation);
        emp.setStatus("pending");
        emp.setApproved(false);
        TempEmp tempEmp = empService.submitForApproval(emp, operation);
        return new ResponseEntity<>(tempEmp.getId(), HttpStatus.OK);
    }

    @PostMapping("/submitUpdateForApproval")
    public ResponseEntity<Long> submitUpdateForApproval(@RequestBody Emp emp, @RequestParam String operation) {
     //   emp.setOperation(operation);
      //  emp.setStatus("pending");
      //  emp.setApproved(false);
        System.out.println(operation);
        TempEmp tempEmp = empService.submitUpdateForApproval(emp, operation);
        
        return new ResponseEntity<>(tempEmp.getId(), HttpStatus.OK);
    }
    
    @DeleteMapping("/removeRequest/{id}")
    public ResponseEntity<Void> removeRequest(@PathVariable Long id) {
        empService.removeRequest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pendingRequests")
    public ResponseEntity<List<Emp>> getPendingRequests() {
        List<Emp> pendingRequests = empService.getPendingRequests();
        return new ResponseEntity<>(pendingRequests, HttpStatus.OK);
    }

    
    @PostMapping("/update/{id}")
    public ResponseEntity<TempEmp> updateEmp1(@PathVariable Long id, @RequestBody TempEmp emp) {
        if (!empService.getEmployeeById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(id);
        emp.setId(id);
        TempEmp updatedEmp = empService.saveTempEmployee(emp);
        return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
    }
    
    @PutMapping("/approveTempRequest/{id}")
    public ResponseEntity<Void> approveTempRequest(@PathVariable Long id, @RequestParam String approve) {
      System.out.println("Received request to approve TempRequest with id: " + id);

      empService.approveTempRequest(id, approve);

      return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/denyTempRequest/{id}")
    public ResponseEntity<Void> denyTempRequest(@PathVariable Long id) {
        empService.denyTempRequestById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/pendingTempRequests")
    public ResponseEntity<List<TempEmp>> getPendingTempRequests() {
        List<TempEmp> pendingTempRequests = empService.getPendingTempRequests();
        System.out.println(pendingTempRequests);
        return new ResponseEntity<>(pendingTempRequests, HttpStatus.OK);
    }
    

    @DeleteMapping("/submitDelateApproval/{empId}")
    public ResponseEntity<Void> submitDeleteApproval(@PathVariable Long empId) {
        empService.submitDeleteApproval(empId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


