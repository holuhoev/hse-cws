import {CHANGE_STUDENT_FILTER} from "../actionConsts";


export function changeStudentFilter(value) {
    return {
        type: CHANGE_STUDENT_FILTER,
        value
    }
}