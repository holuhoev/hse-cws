import React from 'react';
import LecturerWorkloadFilter from "./LecturerWorkloadFilter";
import LecturerTable from "../../containers/lecturer/LecturerTable";

const LecturerPage = () => (
    <div>
        <LecturerWorkloadFilter/>
        <LecturerTable/>
    </div>
);

export default LecturerPage;