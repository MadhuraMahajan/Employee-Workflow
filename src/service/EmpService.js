// EmpService.js

import axios from 'axios';

const BASE_API_URL = 'http://localhost:8994/api/emps';
const BASE_API_UR = 'http://localhost:8994/api/v1/tasks';

class EmpService {
  saveEmp(emp) {
    return axios.post(`${BASE_API_URL}/save`, emp);
  }

  getAllEmp() {
    return axios.get(BASE_API_URL);
  }

  getEmpById(id) {
    return axios.get(`${BASE_API_URL}/${id}`);
  }

  updateEmp(id, emp) {
    return axios.post(`${BASE_API_URL}/update/${id}`, emp);
  }

  // Add the method for submitting for approval
  submitForApproval(emp, operation) {
    return axios.post(`${BASE_API_URL}/submitForApproval`, emp, { params: { operation } });
}//3 15 4 5 2 25 8 1 18 19 8 1 12 
approveTempRequest(id, approve) {
  console.log(approve);

  return axios.put(`${BASE_API_URL}/approveTempRequest/${id}?approve=${approve}`);
}
submitUpdateForApproval(emp, operation) {
  return axios.post(`${BASE_API_URL}/submitUpdateForApproval`, emp, { params: { operation } });}

  submitDeleteApproval(empId) {
    return axios.delete(`${BASE_API_URL}/submitDeleteApproval/${empId}`);
  }

  saveTask(task) {
    return axios.post(`${BASE_API_UR}/assign`, task);
  }

  getAllAssignedTasks() {
    return axios.get(BASE_API_UR + "/all");
  }
  getAssignedTasksByEmpId(empId) {
    console.log(empId);
    return axios.get(`${BASE_API_UR}/byEmpId/${empId}`);
  }
}
export default new EmpService();
