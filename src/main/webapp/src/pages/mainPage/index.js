import React, {Component} from 'react';
import '../../App.css';
import {Link} from 'react-router-dom';

class NormalTypeMainPage extends Component {

  linkStyle = {
    color: 'rgb(90, 2, 21)',
    textDecoration: "none"
  };

  render() {
    console.log(localStorage.getItem("isAuthenticated"));
    return (
      <div className = "WelcomeMainPage">
        <h1 style={{color: "black"}}>Welcome to the</h1>
        <h1 style={{fontSize: "64px"}}>Event Management System</h1>
        <h1 style={{color: "darkblue"}}>Please <Link style = {this.linkStyle} to = "/login">Login</Link> or <Link style = {this.linkStyle} to = "/register">Register</Link> to Continue!</h1>
      </div>
    );
  }


}

export default NormalTypeMainPage;
