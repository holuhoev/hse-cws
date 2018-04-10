import fetch from "isomorphic-fetch";

export const STUDENTS_REQUEST = 'STUDENTS_REQUEST';
export const STUDENTS_RECEIVE = 'STUDENTS_RECEIVE';
export const SELECT_STUDENT = 'SELECT_STUDENT';
export const CHANGE_SEARCH_STRING = 'CHANGE_SEARCH_STRING';

function normalize(data = []) {
    let result = {};
    data.forEach((value => {
        result[value.id] = value;
    }));
}

export function changeSearchString(searchQuery) {
    return {
        type: CHANGE_SEARCH_STRING,
        searchQuery
    }
}

export function studentsRequest(params) {
    return {
        type: STUDENTS_REQUEST,
        params
    }
}

export function selectStudent(student) {
    return {
        type: SELECT_STUDENT,
        student
    }
}

export function studentsReceive(json, params) {
    return {
        type: STUDENTS_RECEIVE,
        data: json.result,
        receivedAt: Date.now(),
        params
    }
}

export function fetchStudents(params) {
    return function (dispatch) {
        dispatch(studentsRequest(params));
        let url = new URL('http://localhost:8080/api/student/getAll');
        if (params) {
            Object.keys(params).forEach(key => {
                if (params[key]) {
                    url.searchParams.append(key, params[key])
                }
            });
        }
        return fetch(url)
            .then(response => response.json())
            .then(json => dispatch(studentsReceive(json, params)));

    };
}
