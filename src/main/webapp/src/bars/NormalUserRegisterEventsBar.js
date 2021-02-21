import React from 'react';
import '../App.css';
import Image from 'react-image-resizer';
import {Link} from 'react-router-dom';
import logo from './bilgem_foto.png';
import { Button } from '@material-ui/core';
import history from "../services/history";
import authService from '../services/authService';

const handleLogOutClick = () => {
    authService.logout();
    localStorage.setItem("isAuthenticated", "false");
    localStorage.setItem("error", "false");
    localStorage.setItem("errorMessage", "");
    history.push("/");
    window.location.reload(false);
    console.log("aaa");
}

export default function NormalUserRegisterEventsBar(){
    const navStyle = {
        color: 'white',
        textDecoration: "none"
    };

    return (
        <nav>
            <Image className = "image" src = {logo} alt = "Logo" height = {100}></Image>
            <ul className = "nav-links">
                <Button>
                    <Link style = {navStyle} to = "/registerEvents">
                        Register Events
                    </Link>
                </Button>
                <Button style={{backgroundColor: "white", color : "black"}}  onClick = {handleLogOutClick}>
                    Logout
                </Button>
                
            </ul>
        </nav>
    )

}