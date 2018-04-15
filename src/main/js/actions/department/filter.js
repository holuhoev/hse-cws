import {CHANGE_DEPARTMENT_FILTER} from "../actionConsts";

export const changeDepartmentFilter = (value) => {
    return {
        type: CHANGE_DEPARTMENT_FILTER,
        value
    }
}