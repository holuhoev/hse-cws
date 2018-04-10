import {
    STUDENT_DISCIPLINE_WORKLOAD_REQUEST,
    STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
    SELECT_GROUP
} from "../actions";
import {combineReducers} from "redux";
import {CHANGE_SEARCH_STRING, STUDENTS_RECEIVE, STUDENTS_REQUEST,SELECT_STUDENT} from "../actions/students";


let filterInitState = {
    selectedStudent: undefined,
    selectedGroup: undefined,
    searchString: ''
};

function filter(state = filterInitState, action) {
    switch (action.type) {
        case SELECT_STUDENT:
            return Object.assign({}, state, {
                selectedStudent: action.student
            });
        case SELECT_GROUP:
            return Object.assign({}, state, {
                selectedGroup: action.group
            });
        case CHANGE_SEARCH_STRING:
            return Object.assign({}, state, {
                searchString: action.searchString
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

