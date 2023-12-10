package com.employee_workflow.Employee_System_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.employee_workflow.Employee_System_backend.model.Emp;
import com.employee_workflow.Employee_System_backend.model.TempEmp;
import com.employee_workflow.Employee_System_backend.repository.EmpReopsitory;
import com.employee_workflow.Employee_System_backend.repository.TempEmpRepository;

import jakarta.transaction.Transactional;

@Service
public class EmpServiceImpl implements EmpService {
	  private EmpReopsitory employeeRepository;
      private TempEmpRepository tempEmpRepository;
	    @Autowired
	    public void setEmployeeRepository(EmpReopsitory employeeRepository) {
	        this.employeeRepository = employeeRepository;
	    }
	    @Autowired
	    public void setTempEmpRepository(TempEmpRepository tempEmpRepository) {
	        this.tempEmpRepository = tempEmpRepository;
	    }
    @Override
    public List<Emp> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Emp> getEmployeeById(Long empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public Emp saveEmployee(Emp employee) {
        return employeeRepository.save(employee);
    }
 
    @Override
    public TempEmp saveTempEmployee(TempEmp emp) {
        return tempEmpRepository.save(emp);
    }

    @Override
    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public List<Emp> getPendingRequests() {
        return employeeRepository.findByStatus("pending");
    }


    @Override
    public TempEmp submitForApproval(Emp emp, String operation) {
        TempEmp tempEmp = new TempEmp();
        tempEmp.setEmpname(emp.getEmpname());
        tempEmp.setEmail(emp.getEmail());
        tempEmp.setAddress(emp.getAddress());
        tempEmp.setPhoneNo(emp.getPhoneNo());
        tempEmp.setEmpId(emp.getEmpId());
        tempEmp.setPassword(emp.getPassword());
        tempEmp.setStatus("pending");
        tempEmp.setOperation(operation);
        tempEmp.setApproved(false);

        return tempEmpRepository.save(tempEmp);
    }



    @Override
    @Transactional
    public void approveTempRequest(Long empId, String approveString) {
      Optional<TempEmp> optionalTempEmp = tempEmpRepository.findByEmpId(empId);

      if (optionalTempEmp.isPresent()) {
        TempEmp tempEmp = optionalTempEmp.get();
//System.out.println(approveString);
        if ("allow".equals(approveString)) {
        //	System.out.println(tempEmp.getOperation());
          if ("create".equals(tempEmp.getOperation())) {
            Emp emp = convertTempEmpToEmp(tempEmp);
            emp.setStatus("approved");
            emp.setApproved(true);

            // Save the employee to the main table (Emp)
            saveEmployee(emp);
            tempEmpRepository.deleteByEmpId(empId);
          } else if ("update".equals(tempEmp.getOperation())) {
              Optional<Emp> optionalTempEmp1 = employeeRepository.findByEmpId(empId);
              if (optionalTempEmp1.isPresent()) {
                  Emp existingEmp = optionalTempEmp1.get();
//        	  Long a=(long) tempEmp.getEmpId();
//            Emp existingEmp =  employeeRepository.findByEmpId(a);
//            if (existingEmp != null) {
              // Update the existing employee with the new data from tempEmp
              existingEmp.setEmpname(tempEmp.getEmpname());
              existingEmp.setEmail(tempEmp.getEmail());
              existingEmp.setAddress(tempEmp.getAddress());
              existingEmp.setPhoneNo(tempEmp.getPhoneNo());
              existingEmp.setPassword(tempEmp.getPassword());
              existingEmp.setStatus("approved");
              existingEmp.setApproved(true);
              // Save the updated employee to the main table (Emp)
              saveEmployee(existingEmp);
              tempEmpRepository.deleteByEmpId(empId);

            }
          } else if ("delete".equals(tempEmp.getOperation())) {
            // Delete the employee from the main table (Emp)
        	  tempEmpRepository.deleteByEmpId(empId);
        	  employeeRepository.deleteByEmpId(empId); 
        	  Long b=(long) tempEmp.getEmpId();
            deleteEmployee(b);
          }

          // Delete the temporary record from TempEmp
          tempEmpRepository.deleteByEmpId(empId);
        } else {
          // Deny request: Remove the temporary record from TempEmp
          tempEmpRepository.deleteByEmpId(empId);
        }
       
      }
    }



    @Override
    @Transactional
    public void denyTempRequestById(Long empId) {
        // Deny request: Remove the temporary record
        tempEmpRepository.deleteById(empId);
    }

    private Emp convertTempEmpToEmp(TempEmp tempEmp) {
        Emp emp = new Emp();
        emp.setEmpname(tempEmp.getEmpname());
        emp.setEmail(tempEmp.getEmail());
        emp.setAddress(tempEmp.getAddress());
        emp.setPhoneNo(tempEmp.getPhoneNo());
        emp.setEmpId(tempEmp.getEmpId());
        emp.setPassword(tempEmp.getPassword());
        return emp;
    }

 // Implement the method in EmpServiceImpl
 @Override
 public List<TempEmp> getPendingTempRequests() {
     return tempEmpRepository.findByStatus("pending");
 }


   

    public void removeRequest(Long empId) {
        deleteEmployee(empId);
    }
	@Override
    @Transactional
	public void denyTempRequest(Long empId) {
		Optional<Emp> optionalEmp = getEmployeeById(empId);

        if (optionalEmp.isPresent()) {
            // If denied, remove the request from the database
            deleteEmployee(empId);
        }
		
	}
	@Override
	public void denyRequest(Long empId) {
		// TODO Auto-generated method stub
		
	}


    @Override
    @Transactional
    public TempEmp submitUpdateForApproval(Emp emp, String operation) {
        TempEmp tempEmp = new TempEmp();
        tempEmp.setEmpname(emp.getEmpname());
        tempEmp.setEmail(emp.getEmail());
        tempEmp.setAddress(emp.getAddress());
        tempEmp.setPhoneNo(emp.getPhoneNo());
        tempEmp.setEmpId(emp.getEmpId());
        tempEmp.setPassword(emp.getPassword());
        tempEmp.setStatus("pending");
        tempEmp.setOperation(operation); // Ensure that the operation is set correctly to 'update'
        tempEmp.setApproved(false);

        return tempEmpRepository.save(tempEmp);
    }
    @Override
    @Transactional
    public void submitDeleteApproval(Long empId) {
    	Optional<Emp> empOptional = employeeRepository.findByEmpId(empId);

       // System.out.println(empOptional);
        System.out.println(empId);

        if (empOptional.isPresent()) {
            Emp emp = empOptional.get();
            System.out.println(emp);
            TempEmp tempEmp = new TempEmp();
            tempEmp.setEmpname(emp.getEmpname());
            tempEmp.setEmail(emp.getEmail());
            tempEmp.setAddress(emp.getAddress());
            tempEmp.setPhoneNo(emp.getPhoneNo());
            tempEmp.setEmpId(emp.getEmpId());
            tempEmp.setPassword(emp.getPassword());
            tempEmp.setStatus("pending");
            tempEmp.setOperation("delete");
            tempEmp.setApproved(false);
            tempEmpRepository.save(tempEmp);
        //    employeeRepository.deleteById(empId); 
        //    tempEmpRepository.deleteByEmpId(empId);// Optionally, delete the employee from the main table
        }
        else {
        	
        }
    }
    
}



