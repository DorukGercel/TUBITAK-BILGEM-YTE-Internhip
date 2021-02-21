import React, {Component} from 'react';
import PaginationTable from "./components/table/PaginationTable";
import axios from "axios";
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";

class RegisterEvents extends Component {

  eventAddDialogFields = [
    {id: 'eventNo', label: 'Event No', minWidth: 170},
    {id: 'name', label: 'Event Name', minWidth: 100},
    {id: 'address', label: 'Address', minWidth: 170, align: 'right',},
    {id: 'capacity', label: 'Capacity', minWidth: 170, align: 'right',},
    {id: 'startDate', label: 'Start Date', format: "dd/MM/yyyy HH:mm:ss",minWidth: 170, align: 'right',},
    {id: 'endDate', label: 'End Date', format: "dd/MM/yyyy HH:mm:ss",minWidth: 170, align: 'right',},
  ]


  constructor() {
    super();
    this.state = {
      currentExtension: "/normalUser",
      slash: "/",
      rows: [],
      addEventModalOpen: false,
      currentEventNo: "",
      updateEventModalOpen: false,
      snackbarProperties: {
        isOpen: false,
        message: "",
        severity: ""
      }
    }
  }

  componentDidMount() {
    axios.get(this.state.currentExtension + "/listRelatedEvents")
      .then(response => {
        this.setState({rows: response.data})
      })
      .catch(error => {
        if (error.response.status === 403) {
          console.log("adam arıza")
        }
      })
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


  onEventRegister = (eventNo) => {
    axios.post(this.state.currentExtension + "/registerEvents" + this.state.slash + localStorage.getItem("tcKimlikNo") + this.state.slash +eventNo)
      .then(response => {
        if(response.data.capacity >= 0){
          var changed_row = this.state.rows.filter(row => row.eventNo === eventNo)[0];
          changed_row.numberOfParticipants = changed_row.numberOfParticipants + 1;
          this.setState(prevState => (
            { 
              rows: [...prevState.rows.map(row => row.eventNo !== eventNo ? row : changed_row)]}
            ));
          this.snackbarOpen("You have successfuly registered the event!", "success");
        }
        else if(response.data.capacity >= -1){
          this.snackbarOpen("You are already registered to this event!", "error");
        }
        else{
          this.snackbarOpen("Capacity full!", "error");
        }
      })
      .catch(err =>{
         this.snackbarOpen("Register to event failed for a reason!", "error");
        }
      )
  }

  render() {

    return (
      <div className="ManageEvents" style = {{padding : "15px"}}>
        <Snackbar open={this.state.snackbarProperties.isOpen} autoHideDuration={5000} onClose={this.snackbarClose}
                  anchorOrigin={{vertical: 'top', horizontal: 'right'}}>
          <Alert onClose={this.snackbarClose} severity={this.state.snackbarProperties.severity}>
            {this.state.snackbarProperties.message}
          </Alert>
        </Snackbar>
        <PaginationTable rows={this.state.rows} onEventRegister={this.onEventRegister} />
      </div>
    );
  }


}

export default RegisterEvents;
