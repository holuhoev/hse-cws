import {combineReducers} from "redux";
import {studentFilter} from "./StudentFilter";
import {workloadFilter} from "./WorkloadFilter";
import {students} from "./Students";
import {workloads} from "./Workloads";
import {groups} from "./Groups";
import {faculties} from "./Faculties";
import {institutes} from "./Institutes";


const studentDisciplineWorkload = combineReducers({
    studentFilter,
    workloadFilter,
    students,
    groups,
    faculties,
    institutes,
    workloads
});

export default studentDisciplineWorkload;

