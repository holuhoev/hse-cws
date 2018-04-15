import React from 'react';
import {Segment} from "semantic-ui-react";
import DepartmentTable from "../../containers/department/DepartmentTable";
import ChairDropDown from "../../containers/department/ChairDropDown";
import FromDatePicker from "../../containers/department/FromDatePicker";
import ToDatePicker from "../../containers/department/ToDatePicker";

const DepartmentPage = () => (
    <Segment.Group>
        <Segment color='blue' secondary>Фильтр преподавателей.</Segment>
        <Segment.Group horizontal>
            <Segment><ChairDropDown/></Segment>
            <Segment><FromDatePicker/></Segment>
            <Segment><ToDatePicker/></Segment>
        </Segment.Group>
        <DepartmentTable/>
    </Segment.Group>
);

export default DepartmentPage;