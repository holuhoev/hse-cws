import {
    SELECT_GROUP,
    SELECT_STUDENT,
    STUDENT_DISCIPLINE_WORKLOAD_REQUEST,
    STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
    STUDENTS_REQUEST,
    STUDENTS_RECEIVE
} from "../actions";

import {students} from "./student";

let studentDisciplineWorkloadState = {
    group: undefined,
    student: undefined,
    groups: {},
    students: {},
    lastUpdate: undefined,
    items: [],
    isFetching: false
};

export function studentDisciplineWorkload(state = studentDisciplineWorkloadState, action) {
    switch (action.type) {
        case SELECT_GROUP:
            return Object.assign({}, state, {
                group: action.group
            });
        case SELECT_STUDENT:
            return Object.assign({}, state, {
                student: action.student
            });
        case STUDENT_DISCIPLINE_WORKLOAD_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case STUDENT_DISCIPLINE_WORKLOAD_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data,
                lastUpdate: action.receivedAt
            });
        case STUDENTS_RECEIVE:
        case STUDENTS_REQUEST:
            return Object.assign({}, state, {
                students: students(state.students, action)
            });
        default:
            return state;
    }
}

