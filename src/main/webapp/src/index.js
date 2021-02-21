import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

import * as serviceWorker from './serviceWorker';
import { setAuthorizationToken } from "./helpers/setAuthorizationToken";


const jwtToken = localStorage.getItem("jwtToken");
if (jwtToken) {
    setAuthorizationToken(jwtToken);
}


ReactDOM.render(
  <div>
    <App />
  </div>,
  document.getElementById('root')
);

serviceWorker.unregister();