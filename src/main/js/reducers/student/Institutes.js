import {INSTITUTES_REQUEST, INSTITUTES_RECEIVE} from "../../actions/actionConsts";


const initState = {
    isFetching: false,
    items: []
};

export function institutes(state = initState, action) {
    switch (action.type) {
        case INSTITUTES_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case INSTITUTES_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}