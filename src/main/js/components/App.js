import React from 'react';
import WorkloadFilter from '../containers/student/WorkloadFilter'
import StudentTable from "../containers/student/StudentTable";
import StudentFilter from '../containers/student/StudentFilter'

const App = () => (
    <div>
        <StudentFilter/>
        <WorkloadFilter/>
        <StudentTable/>
    </div>
);

export default App;