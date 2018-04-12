import React from 'react';
import StudentDropDown from "../containers/student/StudentDropDown";
import FromDatePicker from "../containers/student/FromDatePicker";
import ToDatePicker from "../containers/student/ToDatePicker";


const StudentWorkloadFilter = () => (
    <div>
        <StudentDropDown/>
        <FromDatePicker/>
        <ToDatePicker/>
    </div>
);

export default StudentWorkloadFilter;