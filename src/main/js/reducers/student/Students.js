import {STUDENTS_RECEIVE, STUDENTS_REQUEST} from "../../actions/students";

let studentsInitState = {
    isFetching: false,
    items: []
};

export function students(state = studentsInitState, action) {
    switch (action.type) {
        case STUDENTS_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case STUDENTS_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}