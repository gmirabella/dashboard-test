import React, { useState, useEffect } from 'react';
import axios from 'axios';

import './CountryPicker.css';

const CountryPicker = props => {
  const [loadedCountries, setLoadedCountries] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    axios.get('http://docker.for.mac.localhost:8080/filters')
        .then(response => {
            //console.log("res", response)
            setLoadedCountries(response.data.countries)
            setIsLoading(false)
        }).catch(err =>
            console.log(err))
  }, []) 

  let content = <p>Loading countries...</p>;

  if (!isLoading && loadedCountries && loadedCountries.length > 0) {
    content = (
      <select
        onChange={props.onCountrySelect}
        value={props.selectedCountry}
        className={props.side}
      >
        {loadedCountries.map(country => (
          <option key={country.isoCode} value={country.id}>
            {country.name}
          </option>
        ))}
      </select>
    );
  } else if (!isLoading && (!loadedCountries || loadedCountries.length === 0)) {
    content = <p>Could not fetch any data.</p>;
  }
  return content;
};

export default CountryPicker;
