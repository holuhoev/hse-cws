import {combineReducers} from "redux";
import studentDisciplineWorkload from './student'
import lecturerDisciplineWorkload from './lecturer'

const rootReducer = combineReducers({
    studentDisciplineWorkload,
    lecturerDisciplineWorkload
});

export default rootReducer;