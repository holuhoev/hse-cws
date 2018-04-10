import {SELECT_STUDENT} from "../../actions/students";


const initState = {
    student: undefined,
    fromDate: undefined,
    toDate: undefined
};

export function workloadFilter(state = initState, action) {
    switch (action.type) {
        case SELECT_STUDENT:
            return Object.assign({}, state, {
                student: action.student
            });
        default:
            return state;
    }
}