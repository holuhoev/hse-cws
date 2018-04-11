export const SELECT_STUDENT_FROMDATE = 'SELECT_STUDENT_FROMDATE';
export const SELECT_STUDENT_TODATE = 'SELECT_STUDENT_TODATE';

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