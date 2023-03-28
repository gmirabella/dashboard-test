import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Dropdown, Header, Image } from 'semantic-ui-react'
import './Dashboard.css';

const Dashboard = props => {

  const [isLoading, setIsLoading] = useState(true);
  const [loadedCountries, setLoadedCountries] = useState([]);
  const [loadedDayParts, setLoadedDayParts] = useState([]);
  const [loadedPeriod, setLoadedPeriod] = useState([]);
  
  useEffect(() => {
    axios.get('http://docker.for.mac.localhost:8080/filters')
        .then(response => {
            setLoadedCountries(response.data.countries);
            setLoadedDayParts(response.data.dayParts);
            setLoadedPeriod(response.data.periodDays);
            setIsLoading(false);
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }).catch(err =>
            console.log(err))
  }, []); 

  let content = <div>Loading data...</div>
  if (!isLoading && loadedCountries && loadedCountries.length > 0 && loadedDayParts && loadedDayParts.length >0) {
    content = (
      <div className="dashboard">
        < Header as='h3'><Image circular src='../download-flat.png'/>Dashboard Downloads</Header>
        <h1> Filter by Country: </h1> 
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
            onChange={(e, {value }) => {
              props.setSelectedCountry(value)
              props.setIsChanging(true)
            }}
          />

        <br/>
        <br/>
        <h1> Filter by Day Parts: </h1> 
          <Dropdown 
            fluid
            selection
            placeholder="Select dayPart"
            clearable 
            options={loadedDayParts.map(part => {
                return {
                    key: part,
                    text: part,
                    value: part
                }
            })} 
            onChange={(e, {value }) => {
              props.setSelectedDayParts(value)
              props.setIsChanging(true)
            }}
          />

        <br/>
        <br/>
        <h1> Filter by Period: </h1> 
          <Dropdown 
            fluid
            selection
            placeholder="Select period"
            clearable 
            options={loadedPeriod.map(period => {
                return {
                    key: period,
                    text: period,
                    value: period
                }
            })} 
            onChange={(e, {value }) => {
              props.setSelectedPeriod(value)
              props.setIsChanging(true)
            }}
          />
        <Header align="left" sub >total downloads : {props.loadedDownloads.count}</Header>
      </div>
      );
  } else if (!isLoading && (!loadedCountries || loadedCountries.length === 0) && (!loadedDayParts || loadedDayParts.length === 0)) {
    content = <p><strong>Error!</strong> Could not fetch any data</p>
  }
  return content;
};

export default Dashboard
