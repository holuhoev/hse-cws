import {
    SELECT_STUDENT,
    STUDENTS_REQUEST,
    STUDENTS_RECEIVE,
    STUDENT_DISCIPLINE_WORKLOAD_REQUEST,
    STUDENT_DISCIPLINE_WORKLOAD_RECEIVE
} from "../actions";
import {combineReducers} from "redux";


let filterInitState = {
    selectedStudent: undefined,
};

function filter(state = filterInitState, action) {
    switch (action.type) {
        case SELECT_STUDENT:
            return Object.assign({}, state, {
                selectedStudent: action.student
            });
        default:
            return state;
    }
}

let studentsInitState = {
    isFetching: false,
    items: []
};

function students(state = studentsInitState, action) {
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


let workloadsInitState = {
    isFetching: false,
    items: []
};

function workloads(state = workloadsInitState, action) {
    switch (action.type) {
        case STUDENT_DISCIPLINE_WORKLOAD_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case STUDENT_DISCIPLINE_WORKLOAD_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}

const studentDisciplineWorkload = combineReducers({
    filter,
    students,
    workloads
});
export default studentDisciplineWorkload;

