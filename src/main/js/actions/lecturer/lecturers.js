import {LECTURERS_RECEIVE, LECTURERS_REQUEST} from "../actionConsts";
import fetch from "isomorphic-fetch";
import {HOST, PORT} from "../../config";

export function lecturersRequest(params) {
    return {
        type: LECTURERS_REQUEST,
        params
    }
}

export function lecturersReceive(json, params) {
    return {
        type: LECTURERS_RECEIVE,
        data: json.result,
        params
    }
}

export function fetchLecturers(params) {
    return function (dispatch) {
        dispatch(lecturersRequest(params));
        let url = new URL(HOST + PORT + '/api/lecturer/getAll');
        if (params) {
            Object.keys(params).forEach(key => {
                if (params[key]) {
                    url.searchParams.append(key, params[key])
                }
            });
        }
        return fetch(url)
            .then(response => response.json())
            .then(json => dispatch(lecturersReceive(json, params)));

    };
}