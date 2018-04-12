import {CHANGE_LECTURER_SEARCH_QUERY, SELECT_CHAIR} from "../../actions/actionConsts";

const initState = {
    chair: undefined,
    searchQuery: ''
};


export function lecturerFilter(state = initState, action) {
    switch (action.type) {
        case SELECT_CHAIR:
            return Object.assign({}, state, {
                chair: action.chair
            });
        case CHANGE_LECTURER_SEARCH_QUERY:
            return Object.assign({}, state, {
                searchQuery: action.searchQuery
            });
        default:
            return state;
    }
}