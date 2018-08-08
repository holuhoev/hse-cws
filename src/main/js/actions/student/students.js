import fetch from "isomorphic-fetch";
import {SELECT_STUDENT, STUDENTS_RECEIVE, STUDENTS_REQUEST} from "../actionConsts";
import {ADDRESS} from "../../config";

function normalize(data = []) {
    let result = {};
    data.forEach((value => {
        result[value.id] = value;
    }));
}


export function studentsRequest(params) {
    return {
        type: STUDENTS_REQUEST,
        params
    }
}

export function selectStudent(studentId) {
    return {
        type: SELECT_STUDENT,
        studentId
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
        let url = new URL(ADDRESS + '/api/student/getAll');
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
