import {CHAIRS_RECEIVE, CHAIRS_REQUEST} from "../../actions/actionConsts";

const initState = {
    isFetching: false,
    items: []
};

export function chairs(state = initState, action) {
    switch (action.type) {
        case CHAIRS_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case CHAIRS_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}