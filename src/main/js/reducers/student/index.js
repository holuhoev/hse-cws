import {combineReducers} from "redux";
import {studentFilter} from "./StudentFilter";
import {workloadFilter} from "./WorkloadFilter";
import {students} from "./Students";
import {workloads} from "./Workloads";


const studentDisciplineWorkload = combineReducers({
    studentFilter,
    workloadFilter,
    students,
    workloads
});

export default studentDisciplineWorkload;

