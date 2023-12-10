import './App.css';
import AddEmp from './components/AddEmp';
import AssignTask from './components/AssignTask';
import EditEmp from './components/EditEmp';
import Home from './components/Home';
import Navbar from './components/Navbar';
import { Routes, Route } from 'react-router-dom';
import Admin from './pages/Admin';
import Login from './components/Login';
import Hrdelete from './components/Hrdelete';
import Hredit from './components/Hredit';
import Hrnavbar from './components/Hrnavbar';
import EmployeeDashboard from './components/EmployeeDashboard';
import empService from './service/EmpService';
import React, { useState } from 'react';
import EmpDashboard from './components/EmpDashboard';

function App() {
  const [empId, setEmpId] = useState(null);

  const handleEmpIdSubmit = (enteredEmpId) => {
    // Check if the entered empId matches any assigned tasks
    empService.getAssignedTasksByEmpId(enteredEmpId)
      .then((response) => {
        const tasks = response.data;
        if (tasks.length > 0) {
          setEmpId(enteredEmpId);
        } else {
          alert('No tasks assigned for the entered Employee ID.');
        }
      })
      .catch((error) => {
        console.error('Error fetching assigned tasks:', error);
      });
  };

  return (
    <div>
      <Routes>
   

        {/* Navbar included for all routes except /admin */}
        <Route
          path="/*"
          element={
            <>
              <Navbar />
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/addemp" element={<AddEmp />} />
                <Route path="/editEmp/:id" element={<EditEmp />} />
                <Route path="/assignTask" element={<AssignTask />} />
              </Routes>
            </>
          }
        />

        {/* Navbar included for all routes except /admin */}
        <Route
          path="/hr/*"
          element={
            <>
              <Hrnavbar />
              <Routes>
                <Route path="/hrdelete" element={<Hrdelete />} />
                <Route path="/hredit" element={<Hredit />} />
              </Routes>
            </>
          }
        />
        <Route
          path="/emp/*"
          element={
            <>
              <Routes>
              
              {empId ? (
          <Route
              path="/*"
            element={<EmployeeDashboard empId={empId} />}
          />
        ) : (
          <Route
               path="/*"
            element={<EmpDashboard onEmpIdSubmit={handleEmpIdSubmit} />}
          />
        )}
              </Routes>
            </>
          }
        />
        {/* <Route path="/emp/*" element={<EmpDashboard onEmpIdSubmit={handleEmpIdSubmit} />} /> */}


        {/* No Navbar for /admin */}
        <Route path="/admin/*" element={<Admin />} />
        <Route path="/login/*" element={<Login />} />
          {/* Conditionally render EmployeeDashboard or EmpDashboard */}
        
      </Routes>
    </div>
  );
}

export default App;
