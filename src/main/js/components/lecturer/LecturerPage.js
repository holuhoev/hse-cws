import React from 'react';
import LecturerWorkloadFilter from "./LecturerWorkloadFilter";
import LecturerTable from "../../containers/lecturer/LecturerTable";
import {Segment} from "semantic-ui-react";
import ChairDropDown from "../../containers/lecturer/ChairDropDown";

const LecturerPage = () => (
    <Segment.Group>
        <Segment color='blue' secondary>Фильтр преподавателей.</Segment>
        <Segment.Group horizontal>
            <Segment><ChairDropDown/></Segment>
        </Segment.Group>
        <Segment color='blue' tertiary>Фильтр загруженности.</Segment>
        <LecturerWorkloadFilter/>
        <LecturerTable/>
    </Segment.Group>
);

export default LecturerPage;