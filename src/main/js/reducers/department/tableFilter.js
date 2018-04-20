import {CHANGE_DEPARTMENT_TABLE_FILTER} from "../../actions/actionConsts";

const initState = {
    searchString: ''
};
export const tableFilter = (state = initState, action) => {
    switch (action.type) {
        case CHANGE_DEPARTMENT_TABLE_FILTER:
            return {
                ...state,
                ...action.value
            };
        default:
            return state;
    }
};