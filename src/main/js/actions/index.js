import fetch from 'isomorphic-fetch'
import {
    GROUPS_RECEIVE, GROUPS_REQUEST, STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
    STUDENT_DISCIPLINE_WORKLOAD_REQUEST
} from "./actionConsts";

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
    const {groups} = state.student;
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

export function studentDisciplineWorkloadRequest(value) {
    return {
        type: STUDENT_DISCIPLINE_WORKLOAD_REQUEST,
        value
    }
}


export function studentDisciplineWorkloadReceive(value) {
    return {
        type: STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
        value
    }
}

export function fetchStudentDisciplineWorkload({studentId, fromDate, toDate}) {
    return function (dispatch) {
        dispatch(studentDisciplineWorkloadRequest({isFetching: true}));
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
            .then(json => dispatch(studentDisciplineWorkloadReceive({isFetching: false, items: json.result})));

    };
}