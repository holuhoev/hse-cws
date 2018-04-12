import React from 'react'
import FromDatePicker from "../containers/lecturer/FromDatePicker";
import ToDatePicker from "../containers/lecturer/ToDatePicker";
import LecturerDropDown from "../containers/lecturer/LecturerDropDown";

const LecturerWorkloadFilter = () => (
    <div>
        <LecturerDropDown/>
        <FromDatePicker/>
        <ToDatePicker/>
    </div>
);

export default LecturerWorkloadFilter;