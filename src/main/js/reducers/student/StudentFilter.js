import {CHANGE_STUDENT_FILTER} from "../../actions/actionConsts";


const initState = {
    institute: undefined,
    faculty: undefined,
    group: undefined,
    searchQuery: ''
};

export function studentFilter(state = initState, action) {
    switch (action.type) {
        case CHANGE_STUDENT_FILTER:
            return {
                ...state,
                ...action.value
            };
        default:
            return state;
    }
}