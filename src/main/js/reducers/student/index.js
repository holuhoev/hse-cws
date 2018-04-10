import {combineReducers} from "redux";
import {studentFilter} from "./StudentFilter";
import {workloadFilter} from "./WorkloadFilter";
import {students} from "./Students";
import {workloads} from "./Workloads";
import {groups} from "./Groups";


const studentDisciplineWorkload = combineReducers({
    studentFilter,
    workloadFilter,
    students,
    groups,
    workloads
});

export default studentDisciplineWorkload;

