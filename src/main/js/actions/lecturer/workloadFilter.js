import {SELECT_LECTURER, SELECT_LECTURER_FROMDATE, SELECT_LECTURER_TODATE} from "../actionConsts";


export function selectLecturerFromDate(fromDate) {
    return {
        type: SELECT_LECTURER_FROMDATE,
        fromDate
    }
}

export function selectLecturerToDate(toDate) {
    return {
        type: SELECT_LECTURER_TODATE,
        toDate
    }
}

export function selectLecturer(lecturerId) {
    return {
        type: SELECT_LECTURER,
        lecturerId
    }
}

