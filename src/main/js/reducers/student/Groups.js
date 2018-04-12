import {GROUPS_RECEIVE, GROUPS_REQUEST} from "../../actions/actionConsts";


const initState = {
    isFetching: false,
    items: []
};

export function groups(state = initState, action) {
    switch (action.type) {
        case GROUPS_REQUEST:
            return Object.assign({}, state, {
                isFetching: true
            });
        case GROUPS_RECEIVE:
            return Object.assign({}, state, {
                isFetching: false,
                items: action.data
            });
        default:
            return state;
    }
}