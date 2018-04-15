import React from 'react'
import {connect} from 'react-redux'
import StudentPage from "../components/student/StudentPage";
import LecturerPage from "../components/lecturer/LecturerPage";
import DepartmentPage from "../components/department/DepartmentPage";

const MainPage = ({activeItem}) => (
    <div>
        {activeItem === 'student'
            ? <StudentPage/>
            : (activeItem === 'lecturer'
                ? <LecturerPage/>
                : <DepartmentPage/>)
        }
    </div>


);

const mapStateToProps = (state) => {
    const {application} = state;
    const {activeItem} = application;
    return {
        activeItem
    }
};

export default connect(
    mapStateToProps
)(MainPage)
