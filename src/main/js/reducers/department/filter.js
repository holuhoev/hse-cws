import {CHANGE_DEPARTMENT_FILTER} from "../../actions/actionConsts";
import moment from "moment";

const initState = {
    chairId: undefined,
    fromDate: moment(),
    toDate: moment()
};


export function filter(state = initState, action) {
    switch (action.type) {
        case CHANGE_DEPARTMENT_FILTER:
            return {
                ...state,
                ...action.value
            };
        default:
            return state;
    }
}