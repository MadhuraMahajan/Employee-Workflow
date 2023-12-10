// EmpDashboard.jsx

import React, { useState } from 'react';
// imports...

const EmpDashboard = ({ onEmpIdSubmit }) => {
    const [enteredEmpId, setEnteredEmpId] = useState('');

    const handleEmpIdSubmit = (e) => {
        e.preventDefault();
        onEmpIdSubmit(enteredEmpId);
    };

    return (
        <div>
            <h1>Employee Dashboard</h1>
            <form onSubmit={handleEmpIdSubmit}>
                <label>
                    Enter Employee ID:
                    <input
                        type="text"
                        value={enteredEmpId}
                        onChange={(e) => setEnteredEmpId(e.target.value)}
                    />
                </label>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default EmpDashboard;
