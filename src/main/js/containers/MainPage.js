import React from 'react'
import {connect} from 'react-redux'
import {Segment, Container, Grid} from "semantic-ui-react";
import StudentPage from "../components/StudentPage";
import LecturerPage from "../components/LecturerPage";

const MainPage = ({activeItem}) => (
    <Grid>
        <Grid.Row>
            <Grid.Column>
                <Segment>
                    <Segment.Group>
                        {activeItem === 'students'
                            ? <StudentPage/>
                            : (activeItem === 'lecturers' ? <LecturerPage/> : <StudentPage/>)}
                    </Segment.Group>
                </Segment>
            </Grid.Column>
        </Grid.Row>
    </Grid>


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
