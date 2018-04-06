import {students} from "./student";
import {STUDENTS_RECEIVE} from "../actions";

let entitiesState = {students: {}};

export function entities(state = entitiesState, action) {
    switch (action.type) {
        case STUDENTS_RECEIVE:
            return Object.assign({}, state, {
                students: students(state.students, action)
            });
        default:
            return state;
    }
}