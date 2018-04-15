
import moment from 'moment';
import {SELECT_STUDENT, SELECT_STUDENT_FROMDATE, SELECT_STUDENT_TODATE} from "../../actions/actionConsts";

const initState = {
    studentId: undefined,
    fromDate: moment(),
    toDate: undefined
};

export function workloadFilter(state = initState, action) {
    switch (action.type) {
        case SELECT_STUDENT:
            return Object.assign({}, state, {
                studentId: action.studentId
            });
        case SELECT_STUDENT_FROMDATE:
            return Object.assign({}, state, {
                fromDate: action.fromDate
            });
        case SELECT_STUDENT_TODATE:
            return Object.assign({}, state, {
                toDate: action.toDate
            });
        default:
            return state;
    }
}