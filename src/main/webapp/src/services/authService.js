import axios from 'axios';
import { setAuthorizationToken } from '../helpers/setAuthorizationToken';

const register = (registerData) => {
    console.log(registerData)
    return axios.post("/registerSystem", registerData)
        .then(user => {
            if (user.data.code === "SUCCESS") {
                const {role, tcKimlikNo, token } = user.data;
                localStorage.setItem("jwtToken", token);
                localStorage.setItem("tcKimlikNo", tcKimlikNo);
                localStorage.setItem("role", role);
                console.log(localStorage.getItem("tcKimlikNo"));
                setAuthorizationToken(token);
            }
            return user.data;
        })
        .catch(err => console.log(err));
}

const login = (loginData) => {
    return axios.post("/loginSystem", loginData)
        .then(user => {
            if (user.data.code === "SUCCESS") {
                const { role ,tcKimlikNo ,token } = user.data;
                localStorage.setItem("jwtToken", token);
                localStorage.setItem("tcKimlikNo", tcKimlikNo);
                localStorage.setItem("role", role);
                console.log(localStorage.getItem("tcKimlikNo"));
                console.log(localStorage.getItem("role"));
                setAuthorizationToken(token);
            }
            return user.data;
        })
        .catch(err => console.log(err));
}

const logout = () => {
    localStorage.removeItem("jwtToken");
    setAuthorizationToken(null);
}

export default { login, logout, register };