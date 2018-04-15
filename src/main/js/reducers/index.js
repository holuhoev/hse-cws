import {combineReducers} from "redux";
import student from './student'
import lecturer from './lecturer'
import {application} from "./Application";
import department from "./department";

const rootReducer = combineReducers({
    student,
    lecturer,
    department,
    application
});

export default rootReducer;