import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Select, Dropdown } from 'semantic-ui-react'
import './Dashboard.css';

const Dashboard = props => {

  const [isLoading, setIsLoading] = useState(true);
  const [loadedCountries, setLoadedCountries] = useState([]);
  const [loadedDayParts, setLoadedDayParts] = useState([]);
  const [selectedDayParts, setSelectedDayParts] = useState(1);
  
  useEffect(() => {
    axios.get('http://docker.for.mac.localhost:8080/filters')
        .then(response => {
            //console.log("res", response)
            setLoadedCountries(response.data.countries)
            setLoadedDayParts(response.data.dayParts)
            setIsLoading(false)
        }).catch(err =>
            console.log(err))
  }, []); 

  let content = <div>Loading data...</div>
  if (!isLoading && loadedCountries && loadedCountries.length > 0) {
    content = (
      <div className="dashboard">
      <br/>
      <h1> Downloads by County: </h1> 
      <Dropdown 
        fluid
        search
        selection
        placeholder="Select country"
        clearable 
        options={loadedCountries.map(country => {
            return {
                key: country.isoCode,
                text: country.name,
                value: country.name
            }
        })} 
        onChange={async (e, {value }) => {
          props.setSelectedCountry(value)
          props.setIsChanging(true)
        }
        
        }
      />
    
      <br/>
      <br/>
      <span className="dashboard__output"> Filter by Day Parts: </span> 
      </div>
    );
  } else if (!isLoading && (!loadedCountries || loadedCountries.length === 0)) {
    content = <p><strong>Error!</strong> Could not fetch any data</p>
  }
  return content;
};

export default Dashboard
