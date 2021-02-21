import React from "react";
import { Route, Switch } from "react-router-dom";

import LogIn from "../pages/loginSystem"
import MainPage from "../pages/mainPage";
import ManageEvents from "../pages/manageEvents";
import Register from "../pages/registerSystem";
import RegisterEvents from "../pages/registerEvents";

import PrivateRoute from "./PrivateRoute";
import MorePrivateRoute from "./MorePrivateRoute";

export default function Routes() {
  return (
    <div>
      <Switch>
        <Route path="/" exact component={MainPage} />
        <Route path="/login" component={LogIn}/>
        <Route path="/register" component={Register}/>
        
        <MorePrivateRoute path="/manageEvents" component={ManageEvents} />
        
        <PrivateRoute path="/registerEvents" component={RegisterEvents}/> 
      </Switch>
    </div>
  );
}
