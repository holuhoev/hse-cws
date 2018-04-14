import React from 'react'
import {connect} from 'react-redux'
import StudentPage from "../components/StudentPage";
import LecturerPage from "../components/LecturerPage";

const MainPage = ({activeItem}) => (
    <div>
        {activeItem === 'students'
            ? <StudentPage/>
            : (activeItem === 'lecturers' ? <LecturerPage/> : <StudentPage/>)}
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
