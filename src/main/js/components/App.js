import React from 'react';
import StudentTable from "../containers/student/StudentTable";
import StudentFilter from '../containers/student/StudentFilter'
import StudentWorkloadFilter from "./StudentWorkloadFilter";
import LecturerWorkloadFilter from "./LecturerWorkloadFilter";
import LecturerTable from "../containers/lecturer/LecturerTable";

const App = () => (
    <div>
        <StudentFilter/>
        <StudentWorkloadFilter/>
        <StudentTable/>
        <LecturerWorkloadFilter/>
        <LecturerTable/>
    </div>
);

export default App;