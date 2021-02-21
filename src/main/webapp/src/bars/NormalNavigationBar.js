import React from 'react';
import '../App.css';
import Image from 'react-image-resizer';
import {Link} from 'react-router-dom';
import logo from './bilgem_foto.png';
import { Button } from '@material-ui/core';

export default function NormalNavigationBar(){
    const navStyle = {
        color: 'white',
        textDecoration: "none"
    };

    return (
        <nav>
            <Image className = "image" src = {logo} alt = "Logo" height = {100}></Image>
            <ul className = "nav-links">
                <Button>
                    <Link style = {navStyle} to = "/">
                        Main Page
                    </Link>
                </Button>
                <Button>
                    <Link style = {navStyle} to = "/login">
                        Login
                    </Link>
                </Button>
                <Button>
                    <Link style = {navStyle} to = "/register">
                        Register
                    </Link>
                </Button>

            </ul>
        </nav>
    )

}