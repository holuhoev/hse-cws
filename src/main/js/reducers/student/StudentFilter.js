import {CHANGE_STUDENT_FILTER} from "../../actions/actionConsts";


const initState = {
    instituteId: undefined,
    facultyId: undefined,
    course: undefined,
    groupId: undefined,
    studentFio: ''
};

export function studentFilter(state = initState, action) {
    switch (action.type) {
        case CHANGE_STUDENT_FILTER:
            return {
                ...state,
                ...action.value
            };
        default:
            return state;
    }
}