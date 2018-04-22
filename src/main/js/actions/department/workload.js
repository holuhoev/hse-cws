import {DEPARTMENT_WORKLOAD_REQUEST, DEPARTMENT_WORKLOAD_RECEIVE, UPDATE_DEPARTMENT_WORKLOAD} from "../actionConsts";
import fetch from "isomorphic-fetch";

const format = 'YYYY-MM-DD';

export function departmentWorkloadRequest(params) {
    return {
        type: DEPARTMENT_WORKLOAD_REQUEST,
        params
    }
}

export const updateDepartmentWorkload = (value) => {
    return {
        type: UPDATE_DEPARTMENT_WORKLOAD,
        value
    }
};

export function departmentWorkloadReceive(params, json) {
    return {
        type: DEPARTMENT_WORKLOAD_RECEIVE,
        params,
        data: json.result,
        receivedAt: Date.now()
    }
}

export function fetchDepartmentWorkload({chairId, fromDate, toDate}) {
    return function (dispatch) {
        dispatch(departmentWorkloadRequest({chairId, fromDate, toDate}));
        let url = new URL('http://localhost:8080/api/lecturer/sumWorkload');
        if (chairId) {
            url.searchParams.append("chairId", chairId);
        }
        if (fromDate) {
            url.searchParams.append("fromDate", fromDate.format(format));
        }
        if (toDate) {
            url.searchParams.append("toDate", toDate.format(format));
        }
        return fetch(url)
            .then(response => response.json())
            .then(json => dispatch(departmentWorkloadReceive({chairId, fromDate, toDate}, json)));

    };
}