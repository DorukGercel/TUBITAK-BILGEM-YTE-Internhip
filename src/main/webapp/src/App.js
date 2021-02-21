import React, {Component} from 'react';
import './App.css';
import { Router } from 'react-router-dom';
import history from './services/history';
import Routes from './routes';
import NormalNavigationBar from './bars/NormalNavigationBar';
import NormalUserRegisterEventsBar from './bars/NormalUserRegisterEventsBar';
import CorpUserManageEventsBar from './bars/CorpUserManageEventsBar';

function DisplayBar(props){
  const isLoggedIn = props.isLoggedIn;
  const role = props.role;
  console.log(isLoggedIn);
  if(isLoggedIn === "true" && role === "NormalUser"){
    return(<NormalUserRegisterEventsBar/>)    
  }
  else if(isLoggedIn === "true" && role === "CorpUser"){
    return(<CorpUserManageEventsBar/>)    
  }
  else{
    return(<NormalNavigationBar/>)
  }
}

class App extends Component {
  render(){
    console.log(localStorage.getItem("jwtToken"));
    return (
      <div className = "App">
        <Router history={history}>
          <DisplayBar isLoggedIn = {localStorage.getItem("isAuthenticated")} role = {localStorage.getItem("role")}/>
          <Routes/>
        </Router>
      </div>
    );
    }
}

export default App;
