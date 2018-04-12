import fetch from 'isomorphic-fetch'
import {
    GROUPS_RECEIVE, GROUPS_REQUEST, SELECT_GROUP, STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
    STUDENT_DISCIPLINE_WORKLOAD_REQUEST
} from "./actionConsts";

export function selectGroup(group) {
    return {
        type: SELECT_GROUP,
        group
    }
}

export function groupsRequest() {
    return {
        type: GROUPS_REQUEST
    }
}

export function groupsReceive(json) {
    return {
        type: GROUPS_RECEIVE,
        data: json.result,
        receivedAt: Date.now()
    }
}

function fetchGroups() {
    return (dispatch) => {
        dispatch(groupsRequest());
        return fetch('http://localhost:8080/api/group')
            .then(response => response.json())
            .then(json => dispatch(groupsReceive(json)));

    };
}

function shouldFetchGroups(state) {
    const {groups} = state.studentDisciplineWorkload;
    return !(groups.items && groups.items.length > 0);
}

export function fetchGroupsIfNeeded() {
    return (dispatch, getState) => {
        if (shouldFetchGroups(getState())) {
            return dispatch(fetchGroups())
        }
    };
}


const format = 'YYYY-MM-DD';

export function studentDisciplineWorkloadRequest(params) {
    return {
        type: STUDENT_DISCIPLINE_WORKLOAD_REQUEST,
        params
    }
}


export function studentDisciplineWorkloadReceive(params, json) {
    return {
        type: STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
        params,
        data: json.result,
        receivedAt: Date.now()
    }
}

export function fetchStudentDisciplineWorkload({studentId, fromDate, toDate}) {
    return function (dispatch) {
        dispatch(studentDisciplineWorkloadRequest({studentId, fromDate, toDate}));
        let url = new URL('http://localhost:8080/api/student/disciplineWorkload');
        if (studentId) {
            url.searchParams.append("studentId", studentId);
        }
        if (fromDate) {
            url.searchParams.append("fromDate", fromDate.format(format));
        }
        if (toDate) {
            url.searchParams.append("toDate", toDate.format(format));
        }
        return fetch(url)
            .then(response => response.json())
            .then(json => dispatch(studentDisciplineWorkloadReceive({studentId, fromDate, toDate}, json)));

    };
}