import React from 'react';
import { Route, Redirect } from 'react-router-dom';

const MorePrivateRoute = ({ component: Component, ...rest }) => {
    return (
        <Route { ...rest } render={ props => (
            (localStorage.getItem("isAuthenticated") === "true" && localStorage.getItem("role") === "CorpUser")? 
            <Component {...props} /> : 
            <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
        ) }
        />
    );
};

export default MorePrivateRoute;