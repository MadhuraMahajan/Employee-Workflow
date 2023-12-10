import React, { useEffect, useState } from 'react'
import empService from "../service/emp.service";
import { useNavigate, useParams } from 'react-router-dom';

const DeleteEmployee = () => {

    const[emp,setEmp]=useState({
        id:"",
        username:"",
        email:"",
        role:"",
        password:"",
        phoneno:"",
    });
    const [msg,setMsg]=useState("");

    const data = useParams();
    const navigate = useNavigate();

    useEffect(()=>{
        empService
        .getEmpById(data.id)
        .then((res) =>{
            setEmp(res.data);
        })
        .catch((error) =>{
            console.log(error);
        });
    })

    const handleChange=(e)=>{
        const value=e.target.value;
        setEmp({...emp, [e.target.name]:value});
    }

    const deleteEmp = () => {
        empService.deleteEmp(emp.id)
            .then(() => {
                setMsg("Emp deleted Successfully");
                navigate("/");
            })
            .catch((error) => {
                console.log(error);
            });
    };

  return (
    <div>
        <div className='container'>
          <div className="row">
            <div className='col-md-6 offset-md-3'>
                <div className="card">
                    <div className="card-header text-center fs-3">
                        Delete Employee
                     { msg && <p className='text-success'>{msg}</p>
                     }
                     </div>

                    <div className="card-body">
                        <form >
                            <div className='mb-3'>
                                <lable>Enter user name</lable>
                                <input type='text' className='form-control' name='username' value={emp.username} onChange={(e)=>handleChange(e)}/>
                            </div>

                            <div className='mb-3'>
                                <lable>Enter email</lable>
                                <input type='email' className='form-control' name='email' value={emp.email}  onChange={(e)=>handleChange(e)}/>
                            </div>
                            <div className='mb-3'>
                                <lable>Enter Phone</lable>
                                <input type='tel' className='form-control' name='phone' value={emp.phone}  onChange={(e)=>handleChange(e)}/>
                            </div>

                            <div className='mb-3'>
                                <lable>Enter role</lable>
                                <input type='text' className='form-control' name='role' value={emp.role} onChange={(e)=>handleChange(e)}/>
                            </div>

                         

                            <div className='text-center'>
                                <button className='btn btn-success'>Delete Employee</button>
                            
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>

    </div>
    </div>
  )
}

export default DeleteEmployee;; 