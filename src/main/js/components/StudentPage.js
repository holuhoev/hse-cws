import React from 'react';
import StudentTable from "../containers/student/StudentTable";
import StudentFilter from '../containers/student/StudentFilter'
import StudentWorkloadFilter from "./StudentWorkloadFilter";

const StudentPage = () => (
    <div>
        <StudentFilter/>
        <StudentWorkloadFilter/>
        <StudentTable/>
    </div>
);

export default StudentPage;