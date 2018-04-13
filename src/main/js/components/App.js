import React from 'react';
import StudentPage from "./StudentPage";
import LecturerPage from "./LecturerPage";
import NavigationBarContainer from "../containers/NavigationBarContainer"

const App = () => (
    <div>
        <NavigationBarContainer/>
        <StudentPage/>
        <LecturerPage/>
    </div>
);

export default App;