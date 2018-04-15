import {combineReducers} from "redux";
import {workloads} from "./workloads";
import {filter} from "./filter";

const department = combineReducers({
    filter,
    workloads
});

export default department;