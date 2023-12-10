import React, { useEffect, useState } from 'react';
import empService from '../service/EmpService';
import { Link } from 'react-router-dom';

const Home = () => {
  const [empList, setEmpList] = useState([]);

  useEffect(() => {
    empService.getAllEmp()
      .then((res) => {
        console.log(res.data);
        setEmpList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className='container'>
      <h1 className='text-center mt-5'>Emp System</h1>

      <table className="table mt-5">
        <thead className='bg_light'>
          <tr>
            <th scope="col">EmpName</th>
            <th scope="col">Email</th>
            <th scope="col">EmpId</th>
            <th scope="col">PhoneNo</th>
          </tr>
        </thead>
        <tbody>
          {
            empList.map((e, num) => (
              <tr key={e.id}>
                <th scope="row">{e.empname}</th>
                <td>{e.email}</td>
                <td>{e.empId}</td>
                <td>{e.phoneNo}</td>
                <td>
                  <Link to={"editEmp/" + e.id} className='btn btn-sm btn-primary'>Edit</Link>
                </td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
};

export default Home;
