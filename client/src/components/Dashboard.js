import React from 'react';
import './Dashboard.css';
import CountryPicker from './CountryPicker'


const Dashboard = props => {
  return (
    <div className="dashboard">
      <h1>Dashboard downloads</h1>
      <p>
        <span className="dashboard__output"> Filter by Country:</span>
      </p>
      <CountryPicker

      />
      <p>
      <span className="dashboard__output"> Filter by DayParts: </span>
      <CountryPicker
      />
      </p>
    </div>
  );
};

export default Dashboard;