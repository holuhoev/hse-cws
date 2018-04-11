import React from 'react';
import StudentDropDown from "../containers/student/StudentDropDown";
import FromDatePicker from "../containers/student/FromDatePicker";
import ToDatePicker from "../containers/student/ToDatePicker";


const WorkloadFilter = () => (
    <div>
        <StudentDropDown/>
        <FromDatePicker/>
        <ToDatePicker/>
    </div>
);

export default WorkloadFilter;