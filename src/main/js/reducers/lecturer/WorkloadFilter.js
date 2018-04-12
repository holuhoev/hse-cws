import moment from "moment/moment";
import {SELECT_LECTURER, SELECT_LECTURER_FROMDATE, SELECT_LECTURER_TODATE} from "../../actions/actionConsts";

const initState = {
    lecturerId: undefined,
    fromDate: moment(),
    toDate: undefined
};

export function workloadFilter(state = initState, action) {
    switch (action.type) {
        case SELECT_LECTURER:
            return Object.assign({}, state, {
                lecturerId: action.student
            });
        case SELECT_LECTURER_FROMDATE:
            return Object.assign({}, state, {
                fromDate: action.fromDate
            });
        case SELECT_LECTURER_TODATE:
            return Object.assign({}, state, {
                toDate: action.toDate
            });
        default:
            return state;
    }
}