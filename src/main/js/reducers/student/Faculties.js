import {FACULTIES_REQUEST, FACULTIES_RECEIVE} from "../../actions/actionConsts";


const initState = {
    isFetching: false,
    items: []
};

export function faculties(state = initState, action) {
    switch (action.type) {
        case FACULTIES_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case FACULTIES_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}