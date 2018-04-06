import {STUDENTS_RECEIVE, STUDENTS_REQUEST} from "../actions";

let studentsState = {
    isFetching: false,
    items: []
};

export function students(state = studentsState, action) {
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
