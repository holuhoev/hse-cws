import fetch from "isomorphic-fetch";
import {INSTITUTES_RECEIVE, INSTITUTES_REQUEST} from "../actionConsts";

export function institutesRequest() {
    return {
        type: INSTITUTES_REQUEST
    }
}

export function institutesReceive(json) {
    return {
        type: INSTITUTES_RECEIVE,
        data: json.result,
        receivedAt: Date.now()
    }
}

function fetchInstitutes() {
    return (dispatch) => {
        dispatch(institutesRequest());
        return fetch('http://localhost:8080/api/institute')
            .then(response => response.json())
            .then(json => dispatch(institutesReceive(json)));

    };
}

function shouldFetchInstitutes(state) {
    const {institutes} = state.student;
    return !(institutes.items && institutes.items.length > 0);
}

export function fetchInstitutesIfNeeded() {
    return (dispatch, getState) => {
        if (shouldFetchInstitutes(getState())) {
            return dispatch(fetchInstitutes())
        }
    };
}