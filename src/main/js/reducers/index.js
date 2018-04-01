import {combineReducers} from "redux";

import {
    SELECT_GROUP,
    INVALIDATE_GROUP,
    STUDENTS_SUM_WORKLOAD_REQUEST,
    STUDENTS_SUM_WORKLOAD_RECEIVE
} from '../actions'

function selectedGroup(state = {}, action) {
    switch (action.type) {
        case SELECT_GROUP:
            return action.group;
        default:
            return state;
    }
}

function students(state = {
    isFetching: false,
    didInvalidate: false,
    items: []
}, action) {
    switch (action.type) {
        case INVALIDATE_GROUP:
            return Object.assign({}, state, {didInvalidate: true});
        case STUDENTS_SUM_WORKLOAD_REQUEST:
            return Object.assign({}, state, {
                isFetching: true,
                didInvalidate: false,
            });
        case STUDENTS_SUM_WORKLOAD_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                didInvalidate: false,
                items: action.students,
                lastUpdated: action.receivedAt
            });
        default:
            return state;
    }
}

function studentsByGroup(state, action) {
    switch (action.type) {
        case INVALIDATE_GROUP:
        case STUDENTS_SUM_WORKLOAD_REQUEST:
        case STUDENTS_SUM_WORKLOAD_RECEIVE:
            return Object.assign({}, state, {
                [action.group]: students(state[action.group], action)
            });
        default:
            return state;
    }
}

const rootReducer = combineReducers({
    studentsByGroup,
    selectedGroup
});

export default rootReducer;