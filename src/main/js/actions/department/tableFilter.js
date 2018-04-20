import {CHANGE_DEPARTMENT_TABLE_FILTER} from "../actionConsts";

export const changeDepartmentTableFilter = (value) => {
    return {
        type: CHANGE_DEPARTMENT_TABLE_FILTER,
        value
    }
};
