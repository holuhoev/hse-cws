import {
    STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
    STUDENT_DISCIPLINE_WORKLOAD_REQUEST
} from "../../actions/actionConsts";

let workloadsInitState = {
    isFetching: false,
    items: []
};

export function workloads(state = workloadsInitState, action) {
    switch (action.type) {
        case STUDENT_DISCIPLINE_WORKLOAD_REQUEST:
        case STUDENT_DISCIPLINE_WORKLOAD_RECEIVE:
            return {
                ...state,
                ...action.value
            };
        default:
            return state;
    }
}