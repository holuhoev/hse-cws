import {CHANGE_LECTURER_SEARCH_QUERY, SELECT_CHAIR} from "../actionConsts";

export function selectChair(chair) {
    return {
        type: SELECT_CHAIR,
        chair
    }
}

export function changeLecturerSearchQuery(searchQuery) {
    return {
        type: CHANGE_LECTURER_SEARCH_QUERY,
        searchQuery
    }
}