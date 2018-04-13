import {combineReducers} from "redux";
import studentDisciplineWorkload from './student'
import lecturerDisciplineWorkload from './lecturer'
import {application} from "./Application";

const rootReducer = combineReducers({
    studentDisciplineWorkload,
    lecturerDisciplineWorkload,
    application
});

export default rootReducer;