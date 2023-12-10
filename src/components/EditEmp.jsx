import React, { useEffect, useState } from 'react';
import empService from "../service/EmpService";
import { useNavigate, useParams } from 'react-router-dom';

const EditEmp = () => {
  const [emp, setEmp] = useState({
    empname: '',
    email: '',
    address: '',
    phoneNo: '',
    empId: 0,
    password: '',
  });

  const [msg, setMsg] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    empService.getEmpById(id)
      .then((res) => {
        // Only set the employee details if they are not already set
        if (!emp.empId) {
          setEmp(res.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }, [id, emp.empId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmp({ ...emp, [name]: value });
  };

// EditEmp.js
const updateEmp = (e) => {
  e.preventDefault();

  // Update data in the emp table


      // Submit data to temp_emp and notify admin for update
      empService.submitUpdateForApproval(emp, 'update') // Make sure 'update' is passed as operation
      .then((response) => {
        const requestId = response.data;
        setMsg(`Emp details submitted for update approval with Request ID: ${requestId}. Waiting for admin approval.`);
      })
      .catch((error) => {
        console.error(error);
      });

      // Redirect to a different page or handle success differently
      navigate('/');
    }
 


  return (
    <div className='container'>
      <div className="row">
        <div className='col-md-6 offset-md-3'>
          <div className="card">
            <div className="card-header text-center fs-3">
              Edit Employee
              {msg && <p className='text-success'>{msg}</p>}
            </div>
            <div className="card-body">
              <form onSubmit={updateEmp}>
                <div className='mb-3'>
                  <label>Enter Empoyee Id</label>
                  <input type='text' className='form-control' name='empId' placeholder='Enter EmpId whose details you want to update' value={emp.empId} onChange={handleChange} readOnly />
                </div>
                <div className='mb-3'>
                  <label>Enter Name</label>
                  <input type='text' className='form-control'
                   name='empname' value={emp.empname}  onChange={(e) => handleChange(e)} />
                </div>

                <div className='mb-3'>
                  <label>Enter EmailId</label>
                  <input type='email' className='form-control' name='email'
                   value={emp.email}  onChange={(e) => handleChange(e)} />
                </div>

                <div className='mb-3'>
                  <label>Enter Address</label>
                  <input type='text' className='form-control' name='address' 
                  value={emp.address}  onChange={(e) => handleChange(e)} />
                </div>

                <div className='mb-3'>
                  <label>Enter Phone</label>
                  <input type='tel' className='form-control' name='phoneNo'
                   value={emp.phoneNo}  onChange={(e) => handleChange(e)} />
                </div>

                <div className='mb-3'>
                  <label>Enter password</label>
                  <input type='password' className='form-control' name='password' 
                  value={emp.password}  onChange={(e) => handleChange(e)} />
                </div>

                <div className='text-center'>
  <button type="submit" className='btn btn-success' onClick={updateEmp}>
    Submit
  </button>
  <input type='reset' className='btn btn-danger ms-2' value="Reset" />
</div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EditEmp;