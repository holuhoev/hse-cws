import {CHANGE_LECTURER_FILTER} from "../actionConsts";

export function changeLecturerFilter(value) {
    return {
        type: CHANGE_LECTURER_FILTER,
        value
    }
}