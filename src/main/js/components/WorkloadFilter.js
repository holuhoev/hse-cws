import React from 'react';
import StudentDropDown from "../containers/student/StudentDropDown";
import FromDatePicker from "../containers/student/FromDatePicker";


const WorkloadFilter = () => (
    <div>
        <StudentDropDown/>
        <FromDatePicker/>
    </div>
);

export default WorkloadFilter;