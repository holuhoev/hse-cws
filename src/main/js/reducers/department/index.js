import {combineReducers} from "redux";
import {workloads} from "./workloads";
import {filter} from "./filter";
import {tableFilter} from "./tableFilter";

const department = combineReducers({
    filter,
    workloads,
    tableFilter
});

export default department;