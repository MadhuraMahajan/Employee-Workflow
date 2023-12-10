import React, { useEffect, useState } from 'react';
import moment from 'moment'; // Import moment library
import empService from '../service/EmpService';

const EmployeeDashboard = ({ empId }) => {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    if (empId) {
      fetchTasks(empId);
    }
  }, [empId]);

  const fetchTasks = async (empId) => {
    try {
      const response = await empService.getAssignedTasksByEmpId(empId);

      // Get the current date using moment
      const currentDate = moment();

      const updatedTasks = response.data.map((task) => {
        // Parse endDate using moment to ensure consistent format
        const endDate = moment(task.endDate, 'YYYY-MM-DD');

        return {
          ...task,
          status: endDate.isBefore(currentDate) ? 'Inactive' : 'Active',
        };
      });

      setTasks(updatedTasks);
    } catch (error) {
      console.log('Error fetching tasks:', error);
    }
  };

  const logout = () => {
    console.log('logout');
  };

  return (
    <div className="container">
      <h1 className="text-center mt-5">Employee Dashboard</h1>

      <div className='mt-4'>
        <table className='table'>
          <thead>
            <tr>
              <th>Task</th>
              <th>Description</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Status</th>
              <th>ClientName</th>
            </tr>
          </thead>
          <tbody>
            {tasks.map((t, index) => (
              <tr key={index}>
                <td>{t.task}</td>
                <td>{t.description}</td>
                <td>{t.startDate}</td>
                <td>{t.endDate}</td>
                <td>{t.status}</td>
                <td>{t.clientName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className='mt-4' style={{ position: 'fixed', bottom: 0, width: '100%', padding: '10px', textAlign: 'center' }}>
        <button className='btn btn-danger' onClick={logout}>
          Logout
        </button>
      </div>
    </div>
  );
};

export default EmployeeDashboard;
