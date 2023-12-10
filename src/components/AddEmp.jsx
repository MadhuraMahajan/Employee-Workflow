import React, { useState } from 'react';
import empService from '../service/EmpService';

const AddEmp = () => {
  const [emp, setEmp] = useState({
    empname: '',
    email: '',
    address: '',
    phoneNo: 0,  // Set initial value to 0 or an appropriate default
    empId: 0,    // 3 15 4 5 2 25 8 1 18 19 8 1 12 
    password: '',
  });
  const [msg, setMsg] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmp({ ...emp, [name]: value });
  };

// AddEmp.js
const submitEmp = (e) => {
  e.preventDefault();

  empService.submitForApproval({ ...emp, phoneNo: emp.phoneNo.toString(), empId: parseInt(emp.empId, 10) }, 'create')
      .then((response) => {
          const requestId = response.data;
          setMsg(`Emp Submitted for Approval with Request ID: ${requestId}. Waiting for admin approval.`);
      })
      .catch((error) => {
          console.error(error);
      });
};

  return (
    <div className='container'>
      <div className='row'>
        <div className='col-md-6 offset-md-3'>
          <div className='card'>
            <div className='card-header text-center fs-3'>
              Add Emp
              {msg && <p className='text-success'>{msg}</p>}
            </div>

            <div className='card-body'>
              <form onSubmit={submitEmp}>
                <div className='mb-3'>
                  <label>Enter Emp name</label>
                  <input
                    type='text'
                    className='form-control'
                    name='empname'
                    value={emp.empname}
                    onChange={(e) => handleChange(e)}
                  />
                </div>

                <div className='mb-3'>
                  <label>Enter email</label>
                  <input
                    type='email'
                    className='form-control'
                    name='email'
                    value={emp.email}
                    onChange={(e) => handleChange(e)}
                  />
                </div>

                <div className='mb-3'>
                  <label>Enter Address</label>
                  <input
                    type='text'
                    className='form-control'
                    name='address'
                    value={emp.address}
                    onChange={(e) => handleChange(e)}
                  />
                </div>

                <div className='mb-3'>
  <label>Enter phone number</label>
  <input
    type='tel'
    className='form-control'
    name='phoneNo'  // Corrected from 'phoneno' to 'phoneNo'
    value={emp.phoneNo}  // Corrected from emp.phoneno to emp.phoneNo
    onChange={(e) => handleChange(e)}
  />
</div>

                <div className='mb-3'>
                  <label>Enter empid</label>
                  <input
                    type='number'
                    className='form-control'
                    name='empId'
                    value={emp.empId}
                    onChange={(e) => handleChange(e)}
                  />
                </div>
                <div className='mb-3'>
                  <label>Enter password</label>
                  <input
                    type='password'
                    className='form-control'
                    name='password'
                    value={emp.password}
                    onChange={(e) => handleChange(e)}
                  />
                </div>

                <div className='text-center'>
                  <button type='submit' className='btn btn-success'>
                    Submit
                  </button>
                </div>
                <div className='text-center'>
                  <input type='reset' className='btn btn-danger ms-2' value='Reset' />
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddEmp;
