import {
    DEPARTMENT_WORKLOAD_RECEIVE, DEPARTMENT_WORKLOAD_REQUEST,
    UPDATE_DEPARTMENT_WORKLOAD
} from "../../actions/actionConsts";

let initState = {
    isFetching: false,
    items: [],
    column: null,
    direction: null
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
        case UPDATE_DEPARTMENT_WORKLOAD:
            return {
                ...state,
                ...action.value
            };
        default:
            return state;
    }
}