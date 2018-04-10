import {SELECT_GROUP} from "../../actions";
import {CHANGE_SEARCH_STRING} from "../../actions/students";


const initState = {
    group: undefined,
    searchQuery: ''
};

export function studentFilter(state = initState, action) {
    switch (action.type) {
        case SELECT_GROUP:
            return Object.assign({}, state, {
                group: action.group
            });
        case CHANGE_SEARCH_STRING:
            return Object.assign({}, state, {
                searchQuery: action.searchQuery
            });
        default:
            return state;
    }
}