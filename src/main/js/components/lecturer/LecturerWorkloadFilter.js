import React from 'react'
import FromDatePicker from "../../containers/lecturer/FromDatePicker";
import ToDatePicker from "../../containers/lecturer/ToDatePicker";
import LecturerDropDown from "../../containers/lecturer/LecturerDropDown";
import {Segment} from 'semantic-ui-react'

const LecturerWorkloadFilter = () => (
    <Segment.Group horizontal>
        <Segment>
            <LecturerDropDown/>
        </Segment>
        <Segment>
            <FromDatePicker/>
        </Segment>
        <Segment>
            <ToDatePicker/>
        </Segment>
    </Segment.Group>
);

export default LecturerWorkloadFilter;