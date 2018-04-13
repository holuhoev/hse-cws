import React from 'react';
import StudentDropDown from "../containers/student/StudentDropDown";
import FromDatePicker from "../containers/student/FromDatePicker";
import ToDatePicker from "../containers/student/ToDatePicker";
import { Button, Icon,Grid } from 'semantic-ui-react'

const StudentWorkloadFilter = () => (
    <div>
        <StudentDropDown/>
        <Grid columns='two' divided>
            <Grid.Row>
                <Grid.Column>
                    С <FromDatePicker/>
                    По <ToDatePicker/>
                </Grid.Column>
            </Grid.Row>
        </Grid>
    </div>
);

export default StudentWorkloadFilter;