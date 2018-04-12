import {LECTURER_DISCIPLINE_WORKLOAD_RECEIVE, LECTURER_DISCIPLINE_WORKLOAD_REQUEST} from "../../actions/actionConsts";

let initState = {
    isFetching: false,
    items: []
};

export function workloads(state = initState, action) {
    switch (action.type) {
        case LECTURER_DISCIPLINE_WORKLOAD_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case LECTURER_DISCIPLINE_WORKLOAD_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}