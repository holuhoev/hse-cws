import {STUDENT_DISCIPLINE_WORKLOAD_RECEIVE, STUDENT_DISCIPLINE_WORKLOAD_REQUEST} from "../../actions";

let workloadsInitState = {
    isFetching: false,
    items: []
};

export function workloads(state = workloadsInitState, action) {
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