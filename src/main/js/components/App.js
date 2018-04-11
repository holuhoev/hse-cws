import React from 'react';
import StudentTable from "../containers/student/StudentTable";
import StudentFilter from '../containers/student/StudentFilter'
import WorkloadFilter from "./WorkloadFilter";

const App = () => (
    <div>
        <StudentFilter/>
        <WorkloadFilter/>
        <StudentTable/>
    </div>
);

export default App;