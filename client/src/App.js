import React,{useState, useEffect} from 'react';
import axios from 'axios';
import './App.css';
import 'mapbox-gl/dist/mapbox-gl.css';
import ReactMapGL, {Popup, Marker} from 'react-map-gl';
import moment from 'moment';
import Dashboard from './components/Dashboard';
//import * as points from "./file/mock-download.json"

function App() {
  const [viewport, setViewport] = useState({
    latitude: 45.4668,
    longitude: 9.1905,
    zoom: 2,
    width: "100vw",
    height: "100vh"
  });

  const [ selected, setSelected ] = useState(null);
  const [selectedCountry, setSelectedCountry] = useState(1);
  const [isLoading, setIsLoading] = useState(true);
  const [loadedDownloads, setLoadedDownloads] = useState([]);

useEffect(() => {
  axios.get('http://docker.for.mac.localhost:8080/downloads')
      .then(response => {
          //console.log("res", response)
          setLoadedDownloads(response.data.downloads)
          setIsLoading(false)
      }).catch(err =>
          console.log(err))
}) 
  return (
  <div className="App">
  { <ReactMapGL 
     {...viewport}
      mapboxApiAccessToken='pk.eyJ1IjoiZ21pcmFiZWxsYSIsImEiOiJjazR3bnp2aDQwbG5uM2twMmc3ZW84YTZyIn0.mW8BnDyD5KGNY2ARrQslwA'
      mapStyle= 'mapbox://styles/gmirabella/ck4x1xoxp0i6k1cnnww6o7pnb'
      onViewportChange= {viewport => { setViewport(viewport)} }
     >
      {loadedDownloads.map(download => (
        <Marker
          key       = {download.id}
          latitude  = {download.position.lat}
          longitude = {download.position.lon}
        >
          <button 
            className = "marker-btn" 
            onClick   = {(event) => { 
              event.preventDefault() 
              setSelected(download) 
            }} > 
            <img src= "/flags-pin-svgrepo-com.svg" alt= "Download Empatica Icon" />
          </button>
        </Marker>
      ))}
      {selected ? (
      <Popup 
        latitude  = {selected.position.lat} 
        longitude = {selected.position.lon}
        onClose = {() => {setSelected(null)}}>
        <div align="left">
        <h3>download</h3>
        <h5> id: {selected.id}
        <br/> country: {selected.countryName}
        <br/> day: {moment(selected.downloadedAt).format('YYYY-MM-DD')}
        <br/> dayPart: {selected.dayPart}
        <br/> hour: {moment(selected.downloadedAt).format("HH:mm")}
        </h5>
        </div>
      </Popup> ): null}
    </ReactMapGL> }} }
    <Dashboard
        selectedCountry={selectedCountry}
      /> 
  </div>
  );
};
export default App;
