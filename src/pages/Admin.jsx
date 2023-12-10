import React, { useEffect, useState } from 'react';
import axios from 'axios';//3 15 4 5 2 25 8 1 18 19 8 1 12 
import empService from '../service/EmpService';
const Admin = () => {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8994/api/emps/pendingTempRequests')
      .then((response) => {
        const pendingRequests = response.data.map((request) => ({
          ...request,
        }));
        setNotifications(pendingRequests);
      })
      .catch((error) => console.error('Error fetching pending requests:', error));
  }, []);

  const handlePermission = (empId, operation, approve) => {
    if (approve !== 'allow' && approve !== 'deny') {
        console.error('Invalid value for "approve" parameter');
        return;
    }

    empService.approveTempRequest(empId, approve)
        .then(() => {
            const updatedNotifications = notifications.filter((notification) => notification.empId !== empId);
            setNotifications(updatedNotifications);
        })
        .catch((error) => console.error('Error updating data:', error));
};
console.log(notifications.operation);
console.log(notifications);

  return (
    <div className='container' style={{ color: 'green' }}>
      <h1 className='text-center mt-5' style={{ color: 'green' }}>Admin Dashboard</h1>
      <table className="table mt-5">
        <thead className='bg-light'>
          <tr>
            <th scope="col">Employee ID</th>
            <th scope="col">Name</th>
            <th scope="col">EmailId</th>
            <th scope="col">Phone</th>
            <th scope="col">Request for</th>
            <th scope="col">Permission</th>
          </tr>
        </thead>
        <tbody>
          {notifications.map((notification) => (
            <tr key={notification.empId}>
              <td>{notification.empId}</td>
              <td>{notification.empname}</td>
              <td>{notification.email}</td> 
              <td>{notification.phoneNo}</td>
              <td>{notification.operation}</td>
              <td>
                {notification.status === 'pending' && (
                  <>
                    <button onClick={() => handlePermission(notification.empId, notification.operation, 'allow')}>Allow</button>
                    <button onClick={() => handlePermission(notification.empId, notification.operation, 'deny')}>Deny</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Admin;