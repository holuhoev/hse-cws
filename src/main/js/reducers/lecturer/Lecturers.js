import {LECTURERS_RECEIVE, LECTURERS_REQUEST} from "../../actions/actionConsts";


let initState = {
    isFetching: false,
    items: []
};

export function lecturers(state = initState, action) {
    switch (action.type) {
        case LECTURERS_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case LECTURERS_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}