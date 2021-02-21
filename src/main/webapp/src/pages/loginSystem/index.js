import React, {Component} from 'react';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";

import history from "../../services/history";
import LockIcon from '@material-ui/icons/Lock';
import authService from '../../services/authService';

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://material-ui.com/">
        DODO-NET      
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(8),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));


class LogIn extends Component {

  state = {
    inputData : "",
    classes : {useStyles},
    snackbarProperties: {
      isOpen: false,
      message: "",
      severity: ""
    }
  }

  componentDidMount() {
    localStorage.setItem("isAuthenticated", "false");
    localStorage.setItem("error", "false");
    localStorage.setItem("errorMessage", "");
    authService.logout();
  }

  snackbarOpen = (message, severity) => {
    console.log(message, severity);
    this.setState(prevState => {
      let snackbarProperties = {...prevState.snackbarProperties}
      snackbarProperties.isOpen = true;
      snackbarProperties.message = message;
      snackbarProperties.severity = severity;
      return {snackbarProperties};
    })
  }

  snackbarClose = () => {
    this.setState(prevState => {
      let snackbarProperties = {...prevState.snackbarProperties}
      snackbarProperties.isOpen = false;
      snackbarProperties.message = "";
      snackbarProperties.severity = "";
      return {snackbarProperties};
    })
  }

  handleInputChange = (event) => {
    event.persist();
    console.log(event.target);
    this.setState(prevState => {
      console.log(event.target)
      let inputData = {...prevState.inputData};
      inputData[event.target.id] = event.target.value;
      return {inputData};
    })
  }
  
  handleSubmit = e => {
    e.preventDefault();
    const inputData = this.state.inputData;
    authService.login(inputData)
    .then(data => {
      if(data.code === "FAIL"){
        localStorage.setItem("isAuthenticated", "false");
        localStorage.setItem("error", "true");
        localStorage.setItem("errorMessage", "FAIL");
        this.snackbarOpen("Login failed, please check your Username and Password!", "error");
      }
      else{
        localStorage.setItem("isAuthenticated", "true");
        localStorage.setItem("error", "false");
        localStorage.setItem("errorMessage", "");
        
        let role = localStorage.getItem("role");

        if(role === "CorpUser"){
          history.push("/manageEvents");
        }
        else{
          history.push("/registerEvents");
        }
        
        window.location.reload(false);
      }
    }
    )
    .catch(err => {console.log(err); localStorage.setItem("error", "true"); localStorage.setItem("errorMessage", err);
      this.snackbarOpen("Login failed!", "error");
    });
  }
  
  render(){
    return (
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={this.state.classes.paper}>
          <br/>
          <Snackbar open={this.state.snackbarProperties.isOpen} autoHideDuration={5000} onClose={this.snackbarClose}
                  anchorOrigin={{vertical: 'top', horizontal: 'right'}}>
            <Alert onClose={this.snackbarClose} severity={this.state.snackbarProperties.severity}>
              {this.state.snackbarProperties.message}
            </Alert>
          </Snackbar>
          <LockIcon/>
          <Typography component="h1" variant="h5">
            Log in
          </Typography>
          <form className={this.state.classes.form} noValidate>
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="username"
              label="Username"
              name="username"
              autoComplete="username"
              autoFocus
              onChange = {this.handleInputChange}
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              onChange = {this.handleInputChange}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
              className={this.state.classes.submit}
              onClick = {this.handleSubmit}
            >
              Log In
            </Button>
          </form>
        </div>
        <Box mt={8}>
          <Copyright />
        </Box>
      </Container>
    );
  };
}

export default LogIn;