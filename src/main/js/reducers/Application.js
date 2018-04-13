import {SET_ACTIVE_ITEM} from "../actions/actionConsts";

const initState = {
    activeItem: 'students'
};

export function application(state = initState, action) {
    switch (action.type) {
        case SET_ACTIVE_ITEM:
            return Object.assign({}, state, {
                activeItem: action.activeItem
            });
        default:
            return state
    }
}