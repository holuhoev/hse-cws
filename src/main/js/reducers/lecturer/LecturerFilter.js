import {CHANGE_LECTURER_FILTER} from "../../actions/actionConsts";

const initState = {
    chairId: undefined,
    lecturerFio: ''
};


export function lecturerFilter(state = initState, action) {
    switch (action.type) {
        case CHANGE_LECTURER_FILTER:
            return {
                ...state,
                ...action.value
            };
        default:
            return state;
    }
}