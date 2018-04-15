import React from 'react';
import StudentTable from "../../containers/student/StudentTable";
import GroupDropDown from '../../containers/student/GroupDropDown'
import StudentWorkloadFilter from "./StudentWorkloadFilter";
import {Segment} from 'semantic-ui-react'
import FacultyDropDown from "../../containers/student/FacultyDropDown";
import InstituteDropDown from "../../containers/student/InstituteDropDown";

const StudentPage = () => (
    <Segment.Group>
        <Segment>Фильтр студентов.</Segment>
        <Segment.Group horizontal>
            <Segment><InstituteDropDown/></Segment>
            <Segment><FacultyDropDown/></Segment>
        </Segment.Group>
        <Segment.Group horizontal>
            <Segment><GroupDropDown/></Segment>
            <Segment><GroupDropDown/></Segment>
        </Segment.Group>
        <Segment>Фильтр загруженности.</Segment>
        <StudentWorkloadFilter/>
        <StudentTable/>
    </Segment.Group>
);

export default StudentPage;