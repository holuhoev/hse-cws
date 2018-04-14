import React from 'react';
import StudentTable from "../containers/student/StudentTable";
import StudentFilter from '../containers/student/StudentFilter'
import StudentWorkloadFilter from "./StudentWorkloadFilter";
import {Segment} from 'semantic-ui-react'

const StudentPage = () => (
    <Segment.Group>
        <Segment>Фильтр студентов.</Segment>
        <Segment.Group>
            <Segment><StudentFilter/></Segment>
        </Segment.Group>
        <Segment>Фильтр загруженности.</Segment>
        <Segment.Group>
            <Segment><StudentWorkloadFilter/></Segment>
        </Segment.Group>
        <Segment>
            <StudentTable/>
        </Segment>
    </Segment.Group>
);

export default StudentPage;