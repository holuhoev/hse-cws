import {DEPARTMENT_WORKLOAD_RECEIVE, DEPARTMENT_WORKLOAD_REQUEST} from "../../actions/actionConsts";

let initState = {
    isFetching: false,
    items: []
};

export function workloads(state = initState, action) {
    switch (action.type) {
        case DEPARTMENT_WORKLOAD_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case DEPARTMENT_WORKLOAD_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}