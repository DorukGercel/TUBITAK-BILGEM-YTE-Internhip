import React, {Component} from 'react';
import PaginationTable from "./components/table/PaginationTable";
import Button from "@material-ui/core/Button";
import PlusIcon from '@material-ui/icons/Add';
import axios from "axios";
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import ReactDialog from "./components/dialog/ReactDialog";

class ManageEvents extends Component {

  eventAddDialogFields = [
    {id: 'eventNo', label: 'Event No', minWidth: 170},
    {id: 'name', label: 'Event Name', minWidth: 100},
    {id: 'address', label: 'Address', minWidth: 170, align: 'right',},
    {id: 'capacity', label: 'Capacity', minWidth: 170, align: 'right',},
    {id: 'startDate', label: 'Start Date', format: "dd/MM/yyyy HH:mm:ss",minWidth: 170, align: 'right',},
    {id: 'endDate', label: 'End Date', format: "dd/MM/yyyy HH:mm:ss",minWidth: 170, align: 'right',},
  ]

  eventUpdateDialogFields = [
    {id: 'name', label: 'Event Name', minWidth: 100},
    {id: 'address', label: 'Address', minWidth: 170, align: 'right',},
    {id: 'startDate', label: 'Start Date', minWidth: 170, align: 'right',},
    {id: 'endDate', label: 'End Date', minWidth: 170, align: 'right',},
    {id: 'capacity', label: 'Capacity', minWidth: 170, align: 'right',}
  ]

  constructor() {
    super();
    this.state = {
      currentExtension: "/manageEvents",
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
    axios.get(this.state.currentExtension + "/listAllEvents")
      .then(response => {
        this.setState({rows: response.data})
      })
      .catch(error => {
        if (error.response.status === 403) {
          console.log("adam arıza")
        }
      })
  }

  toggleAddEventModal = () => {
    this.setState({addEventModalOpen: !this.state.addEventModalOpen})
  }

  toggleUpdateEventModal = () => {
    this.setState({updateEventModalOpen: !this.state.updateEventModalOpen})
  }

  setUpdateEventNo = (eventNo) => {
    this.setState({currentEventNo: eventNo})
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

  onEventAdd = (inputData) => {
    axios.post(this.state.currentExtension + "/addNewEvent", inputData)
      .then(response => {
        this.setState(prevState => (
          {rows: [...prevState.rows, response.data]}
          ));
        this.snackbarOpen("Event has been added successfully!", "success");
      })
      .catch(error => {
        if (error.response.status === 400) {
          this.snackbarOpen(error.response.data.errors[0].defaultMessage, "error")
        }
        console.log(error.response);
      })
  }

  onEventUpdate = (eventNo, inputData) => {
    axios.put(this.state.currentExtension + "/updateEvent" + this.state.slash + eventNo, inputData)
      .then(response => {
        var changedRow = this.state.rows.filter(row => row.eventNo === eventNo)[0];
        inputData.numberOfParticipants = changedRow.numberOfParticipants;
        inputData.eventNo = eventNo;
        this.setState(prevState => (
          { 
            rows: [...prevState.rows.map(row => row.eventNo !== eventNo ? row : inputData)]}
          ));
        this.snackbarOpen("Event has been updated successfully!", "success");
      })
      .catch(error => {
        if (error.response.status === 400) {
          this.snackbarOpen(error.response.data.errors[0].defaultMessage, "error")
        }
        console.log(error.response);      })
  }

  onEventDelete = (eventNo) => {
    axios.delete(this.state.currentExtension + "/deleteEvent" + this.state.slash +eventNo)
      .then(response => {
        this.setState( {
          rows: this.state.rows.filter((event) => event.eventNo !== eventNo)
        })
        this.snackbarOpen("Event with Event No " + eventNo + " has been deleted!", "success")
      })
      .catch(error => {error.response.status === 500  ? 
        this.snackbarOpen("There are registered user(s) to this event!","error") :
        this.snackbarOpen("There is a problem!","error");
    }
      )
  }

  render() {

    return (
      <div className="ManageEvents" style = {{padding : "15px"}}>
        <Button variant="contained"
                color="primary"
                style={{float: "right"}}
                onClick={this.toggleAddEventModal}
                startIcon={<PlusIcon/>}>
          Add Event
        </Button>
        <Snackbar open={this.state.snackbarProperties.isOpen} autoHideDuration={5000} onClose={this.snackbarClose}
                  anchorOrigin={{vertical: 'top', horizontal: 'right'}}>
          <Alert onClose={this.snackbarClose} severity={this.state.snackbarProperties.severity}>
            {this.state.snackbarProperties.message}
          </Alert>
        </Snackbar>
        <ReactDialog fields={this.eventAddDialogFields} title="Add Event" isOpen={this.state.addEventModalOpen} onClose={this.toggleAddEventModal}
                         onSubmit={this.onEventAdd} toggle={this.toggleAddEventModal}/>
        <ReactDialog fields={this.eventUpdateDialogFields} title="Update Event" isOpen={this.state.updateEventModalOpen} onClose={this.toggleUpdateEventModal}
                         onSubmit={this.onEventUpdate} eventNo = {this.state.currentEventNo} toggle={this.toggleUpdateEventModal}/>
        <PaginationTable rows={this.state.rows} onUpdate={this.toggleUpdateEventModal} onDelete={this.onEventDelete} setEventNo={this.setUpdateEventNo}/>
      </div>
    );
  }


}

export default ManageEvents;
