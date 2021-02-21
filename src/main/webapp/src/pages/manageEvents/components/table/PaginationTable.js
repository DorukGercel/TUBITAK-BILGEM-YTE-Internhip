import React, {Component} from 'react';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableContainer from '@material-ui/core/TableContainer';
import TableHeader from "./TableHeader";
import TableContent from "./TableContent";
import {TablePageController} from "./TablePageController";

export default class PaginationTable extends Component {

  constructor(props) {
    super(props);
    this.state = {
      page: 0,
      rowsPerPage: 10,
      rows: []
    };
  }

  columns = [
    {id: 'eventNo', label: 'Event No', minWidth: 100},
    {id: 'name', label: 'Event Name', minWidth: 100},
    {id: 'address', label: 'Address', minWidth: 120, align: 'right',},
    {id: 'startDate', label: 'Start Date', minWidth: 110, align: 'right',},
    {id: 'endDate', label: 'End Date', minWidth: 110, align: 'right',},
    {id: 'numberOfParticipants', label: 'Number of Participants', minWidth: 100, align: 'right',},
    {id: 'capacity', label: 'Capacity', minWidth: 100, align: 'right',},
    {id: "update", label: "Update Event", align: "right", onClick: this.props.onUpdate},
    {id: "delete", label: "Delete Event", align: "right", onClick: this.props.onDelete}
  ];

  handleChangePage = (event, newPage) => {
    this.setState({page: newPage});
  };

  handleChangeRowsPerPage = (event) => {
    this.setState({
      rowsPerPage: event.target.value,
      page: 0
    });
  };


  render() {
    return (
      <Paper>
        <TableContainer>
          <Table stickyHeader aria-label="sticky table">
            <TableHeader columns={this.columns}/>
            <TableContent rows={this.props.rows} page={this.state.page} rowsPerPage={this.state.rowsPerPage}
                          columns={this.columns} setEventNo = {this.props.setEventNo}/>
          </Table>
        </TableContainer>
        <TablePageController count={this.state.rows.length}
                             rowsPerPage={this.state.rowsPerPage}
                             page={this.state.page} handleChangePage={this.handleChangePage}
                             handleChangeRowsPerPage={this.handleChangeRowsPerPage}/>
      </Paper>
    );
  }


}
