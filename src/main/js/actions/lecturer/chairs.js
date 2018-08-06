import fetch from "isomorphic-fetch";
import {CHAIRS_RECEIVE, CHAIRS_REQUEST} from "../actionConsts";
import {HOST, PORT} from "../../config";


export function chairsRequest() {
    return {
        type: CHAIRS_REQUEST
    }
}

export function chairsReceive(json) {
    return {
        type: CHAIRS_RECEIVE,
        data: json.result,
        receivedAt: Date.now()
    }
}

function fetchChairs() {
    return (dispatch) => {
        dispatch(chairsRequest());
        return fetch(HOST + PORT + '/api/chair')
            .then(response => response.json())
            .then(json => dispatch(chairsReceive(json)));

    };
}

function shouldFetchChairs(state) {
    const {chairs} = state.lecturer;
    return !(chairs.items && chairs.items.length > 0);
}

export function fetchChairsIfNeeded() {
    return (dispatch, getState) => {
        if (shouldFetchChairs(getState())) {
            return dispatch(fetchChairs())
        }
    };
}