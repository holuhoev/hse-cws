import {SELECT_STUDENT} from "../../actions/student/students";
import {SELECT_STUDENT_FROMDATE} from "../../actions/student/dates";
import moment from 'moment';

const initState = {
    student: undefined,
    fromDate: moment(),
    toDate: undefined
};

export function workloadFilter(state = initState, action) {
    switch (action.type) {
        case SELECT_STUDENT:
            return Object.assign({}, state, {
                student: action.student
            });
        case SELECT_STUDENT_FROMDATE:
            return Object.assign({}, state, {
                fromDate: action.fromDate
            });
        default:
            return state;
    }
}