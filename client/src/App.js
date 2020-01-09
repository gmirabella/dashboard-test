import React,{useState, useEffect} from 'react';
import axios from 'axios';
import './App.css';
import 'mapbox-gl/dist/mapbox-gl.css';
import ReactMapGL, {Popup, Marker} from 'react-map-gl';
import moment from 'moment';
import Dashboard from './components/Dashboard';

function App() {
  const [viewport, setViewport] = useState({
    latitude: 38.707,
    longitude: -9.134,
    zoom: 2.5,
    width: "100vw",
    height: "100vh"
  });

  const [selected, setSelected] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [isChanging, setIsChanging] = useState(false);
  const [loadedDownloads, setLoadedDownloads] = useState([]);
  const [selectedCountry, setSelectedCountry] = useState("");
  const [selectedDayParts, setSelectedDayParts] = useState("");
  const [selectedPeriod, setSelectedPeriod] = useState("");
  const [url, setUrl] = useState('http://localhost:8080/downloads');

useEffect(() =>{
  setUrl('http://localhost:8080/downloads?countryName='+selectedCountry+'&dayPart='+selectedDayParts+'&period='+selectedPeriod);
},[selectedCountry, selectedDayParts, selectedPeriod]);

useEffect(() => {
  //console.log(url)
  axios.get(url)
      .then(response => {
         // console.log("res", response)
          setLoadedDownloads(response.data);
          setIsLoading(false);
          setIsChanging(false);
      }).catch(err =>
          console.log(err));
},[isChanging]);

let content = <div className="lds-ripple"><div></div><div></div></div>
if (!isLoading) {
  
  content = (
<div className="App">
  { <ReactMapGL 
    {...viewport}
    mapboxApiAccessToken='pk.eyJ1IjoiZ21pcmFiZWxsYSIsImEiOiJjazR3bnp2aDQwbG5uM2twMmc3ZW84YTZyIn0.mW8BnDyD5KGNY2ARrQslwA'
    mapStyle= 'mapbox://styles/gmirabella/ck5006q7m2x4l1cqg4afoozeb'
    onViewportChange= {viewport => { setViewport(viewport)} } 
    >
    {loadedDownloads.downloads.map(download => (
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
          <img src= "/marker.svg" alt= "Marker Icon" />
        </button>
      </Marker>
    ))}

    {selected ? (
      <Popup 
        latitude  = {selected.position.lat} 
        longitude = {selected.position.lon}
        onClose = {() => {setSelected(null)}}>
        <div>
          <p><strong>id:</strong> {selected.id}
            <br/><strong>country:</strong> {selected.countryName}
            <br/><strong>day: </strong>{moment(selected.downloadedAt).format('YYYY-MM-DD')}
            <br/><strong>dayPart:</strong> {selected.dayPart}
            <br/><strong>appId: </strong>{selected.appId}
          </p>
        </div>
      </Popup> ): null}
    </ReactMapGL> }
  <Dashboard 
      setIsChanging       = {setIsChanging}
      setSelectedCountry  = {setSelectedCountry}
      setSelectedDayParts = {setSelectedDayParts}
      setSelectedPeriod   = {setSelectedPeriod}
      loadedDownloads     = {loadedDownloads}
  />

</div>
);
  } else if (!isLoading && (!loadedDownloads || loadedDownloads.length === 0)) {
    content = (<div className="alert"><strong>Error!</strong> Could not fetch any data</div>)
  }

  return content;
};
export default App;
