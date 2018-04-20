import {CHANGE_APP_STATE} from "../actions/actionConsts";

const initState = {
    activeItem: 'student',
    showTableFilter: false
};

export function application(state = initState, action) {
    switch (action.type) {
        case CHANGE_APP_STATE:
            return {
                ...state,
                ...action.value
            };
        default:
            return state
    }
}