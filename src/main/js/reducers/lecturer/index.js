import {combineReducers} from "redux";
import {chairs} from "./Chairs";
import {lecturerFilter} from "./LecturerFilter";
import {workloadFilter} from "./WorkloadFilter";
import {lecturers} from "./Lecturers";
import {workloads} from "./Workloads";


const lecturerDisciplineWorkload = combineReducers({
    chairs,
    lecturers,
    lecturerFilter,
    workloadFilter,
    workloads
});

export default lecturerDisciplineWorkload;