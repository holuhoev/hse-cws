import React from 'react';
import StudentDropDown from "../../containers/student/StudentDropDown";
import FromDatePicker from "../../containers/student/FromDatePicker";
import ToDatePicker from "../../containers/student/ToDatePicker";
import {Segment} from 'semantic-ui-react'

const StudentWorkloadFilter = () => (

    <Segment.Group horizontal>
        <Segment>
            <StudentDropDown/>
        </Segment>
        <Segment>
            <FromDatePicker/>
        </Segment>
        <Segment>
            <ToDatePicker/>
        </Segment>
    </Segment.Group>
);

export default StudentWorkloadFilter;