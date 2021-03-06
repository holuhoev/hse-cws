import fetch from "isomorphic-fetch";
import {FACULTIES_RECEIVE, FACULTIES_REQUEST} from "../actionConsts";
import {ADDRESS} from "../../config";

export function facultiesRequest() {
    return {
        type: FACULTIES_REQUEST
    }
}

export function facultiesReceive(json) {
    return {
        type: FACULTIES_RECEIVE,
        data: json.result,
        receivedAt: Date.now()
    }
}

function fetchFaculties() {
    return (dispatch) => {
        dispatch(facultiesRequest());
        return fetch(ADDRESS + '/api/faculty')
            .then(response => response.json())
            .then(json => dispatch(facultiesReceive(json)));

    };
}

function shouldFetchFaculties(state) {
    const {faculties} = state.student;
    return !(faculties.items && faculties.items.length > 0);
}

export function fetchFacultiesIfNeeded() {
    return (dispatch, getState) => {
        if (shouldFetchFaculties(getState())) {
            return dispatch(fetchFaculties())
        }
    };
}