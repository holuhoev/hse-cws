import {SELECT_STUDENT_FROMDATE, SELECT_STUDENT_TODATE} from "../actionConsts";

export function selectStudentFromDate(fromDate) {
    return {
        type: SELECT_STUDENT_FROMDATE,
        fromDate
    }
}

export function selectStudentToDate(toDate) {
    return {
        type: SELECT_STUDENT_TODATE,
        toDate
    }
}