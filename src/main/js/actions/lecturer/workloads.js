import {LECTURER_DISCIPLINE_WORKLOAD_RECEIVE, LECTURER_DISCIPLINE_WORKLOAD_REQUEST} from "../actionConsts";
import fetch from "isomorphic-fetch";
import {HOST, PORT} from "../../config";

const format = 'YYYY-MM-DD';

export function lecturerDisciplineWorkloadRequest(params) {
    return {
        type: LECTURER_DISCIPLINE_WORKLOAD_REQUEST,
        params
    }
}


export function lecturerDisciplineWorkloadReceive(params, json) {
    return {
        type: LECTURER_DISCIPLINE_WORKLOAD_RECEIVE,
        params,
        data: json.result,
        receivedAt: Date.now()
    }
}

export function fetchLecturerDisciplineWorkload({lecturerId, fromDate, toDate}) {
    return function (dispatch) {
        dispatch(lecturerDisciplineWorkloadRequest({lecturerId, fromDate, toDate}));
        let url = new URL(HOST + PORT + '/api/lecturer/disciplineWorkload');
        if (lecturerId) {
            url.searchParams.append("lecturerId", lecturerId);
        }
        if (fromDate) {
            url.searchParams.append("fromDate", fromDate.format(format));
        }
        if (toDate) {
            url.searchParams.append("toDate", toDate.format(format));
        }
        return fetch(url)
            .then(response => response.json())
            .then(json => dispatch(lecturerDisciplineWorkloadReceive({lecturerId, fromDate, toDate}, json)));

    };
}