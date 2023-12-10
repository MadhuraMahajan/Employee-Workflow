import React, { useState } from 'react';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import empService from '../service/EmpService';

const AssignTask = () => {
  const [task, setTask] = useState({
    empId: '', 
    task: '',
    description: '',
    startDate: '',
    endDate: '',
    clientName: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTask({ ...task, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    empService.saveTask(task) // Assuming your service method is named saveTask
      .then((response) => {
        // Handle success if needed
        console.log('Task assigned successfully:', response.data);
        // Optionally, you can reset the form or navigate to a different page.
      })
      .catch((error) => {
        // Handle error if needed
        console.error('Error assigning task:', error);
      });
  };

  return (
    <Container className="mt-5">
      <Row>
        <Col md={{ span: 6, offset: 3 }}>
          <h2>Assign Task</h2>
          <Form onSubmit={handleSubmit}>
            {/* New employee ID field */}
            <Form.Group controlId="empId">
              <label>Employee ID</label>
              <Form.Control
                type="text"
                placeholder="Enter employee ID"
                name="empId"
                value={task.empId}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group controlId="task">
              <label>Task </label>
              <Form.Control
                type="text"
                placeholder="Enter task"
                name="task"
                value={task.task}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group controlId="taskDescription">
              <label>Task Description</label>
              <Form.Control
                type="text"
                placeholder="Enter task description"
                name="description"
                value={task.description}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group controlId="startDate">
              <label>Start Date</label>
              <Form.Control
                type="date"
                name="startDate"
                value={task.startDate}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group controlId="endDate">
              <label>End Date</label>
              <Form.Control
                type="date"
                name="endDate"
                value={task.endDate}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group controlId="clientName">
              <label>Client Name</label>
              <Form.Control
                type="text"
                placeholder="Enter client name"
                name="clientName"
                value={task.clientName}
                onChange={handleChange}
                required
              />
            </Form.Group>
            
            <Button variant="primary" type="submit" className="ml-2">
              Assign Task
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default AssignTask;