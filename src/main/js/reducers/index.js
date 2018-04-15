import {combineReducers} from "redux";
import studentDisciplineWorkload from './student'
import lecturerDisciplineWorkload from './lecturer'
import {application} from "./Application";
import department from "./department";

const rootReducer = combineReducers({
    studentDisciplineWorkload,
    lecturerDisciplineWorkload,
    department,
    application
});

export default rootReducer;