import React from 'react';
import StudentDropDown from "../../containers/student/StudentDropDown";
import FromDatePicker from "../../containers/student/FromDatePicker";
import ToDatePicker from "../../containers/student/ToDatePicker";
import {Button, Icon, Grid} from 'semantic-ui-react'

const StudentWorkloadFilter = () => (
    <Grid columns={3}>
        <Grid.Column>
            <StudentDropDown/>
        </Grid.Column>
        <Grid.Column>
            <FromDatePicker/>
        </Grid.Column>
        <Grid.Column>
            <ToDatePicker/>
        </Grid.Column>
    </Grid>
);

export default StudentWorkloadFilter;