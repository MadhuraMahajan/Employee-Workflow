import React, { useEffect, useState } from 'react';
import empService from '../service/EmpService';
import { Link } from 'react-router-dom';
import axios from 'axios'; // Import axios for HTTP requests

const Hrhome = () => {
  const [empList, setEmpList] = useState([]);

  useEffect(() => {
    empService.getAllEmp().then((res) => {
      console.log(res.data);
      setEmpList(res.data);
    }).catch((error) => {
      console.log(error);
    });
  }, []);

  const handleDelete = (employeeId) => {
    empService.submitDelateApproval(employeeId)
      .then((res) => {
        // Optionally, update the state or refetch data after successful submission
        // ...
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const notifyAdmin = (employeeId, operation) => {
    // Notify admin about the delete operation
    axios.post('/api/admin/notify', { employeeId, operation })
      .then((response) => console.log('Notification sent to admin'))
      .catch((error) => console.error('Error notifying admin:', error));
  };

  return (
    <div className='container'>
      <h1 className='text-center mt-5'>Delete Employee</h1>

      <table className="table mt-5">
        <thead className='bg_light'>
          <tr>
          <th scope="col">EmpId</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">PhoneNo</th>
            
          </tr>
        </thead>
        <tbody>
          {empList.map((employee, num) => (
            <tr key={employee.id}>
              <td>{employee.empId}</td>
              <td>{employee.empname}</td>
              <td>{employee.email}</td>
              <td>{employee.phoneNo}</td>
              <td>
                <button onClick={() => handleDelete(employee.empId)} className='btn btn-sm btn-danger ms-2'>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Hrhome;
