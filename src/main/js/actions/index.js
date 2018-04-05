import fetch from 'isomorphic-fetch'

// TODO: other entities & decompose
export const SELECT_GROUP = 'SELECT GROUP';

export function selectGroup(groupId) {
    return {
        type: SELECT_GROUP,
        groupId
    }
}

export const STUDENTS_REQUEST = 'STUDENTS_REQUEST';
export const STUDENTS_RECEIVE = 'STUDENTS_RECEIVE';
export const SELECT_STUDENT = 'SELECT_STUDENT';

export function studentsRequest(groupId) {
    return {
        type: STUDENTS_REQUEST,
        groupId
    }
}

export function studentsReceive(group, json) {
    return {
        type: STUDENTS_RECEIVE,
        group,
        students: json.result,
        receivedAt: Date.now()
    }
}

export function fetchStudents(group) {
    return function (dispatch) {
        dispatch(studentsRequest(group));
        return fetch('http://localhost:8080/api/student/getAll?groupId=' + group.id)
            .then(response => response.json())
            .then(json => dispatch(studentsReceive(group, json)));

    };
}

export function selectStudent(studentId) {
    return {
        type: SELECT_STUDENT,
        studentId
    }
}

export const STUDENT_DISCIPLINE_WORKLOAD_REQUEST = 'STUDENT_DISCIPLINE_WORKLOAD_REQUEST';
export const STUDENT_DISCIPLINE_WORKLOAD_RECEIVE = 'STUDENT_DISCIPLINE_WORKLOAD_RECEIVE';

export function studentDisciplineWorkloadRequest(groupId) {
    return {
        type: STUDENT_DISCIPLINE_WORKLOAD_REQUEST,
        groupId
    }
}

export function studentDisciplineWorkloadReceive(group, json) {
    return {
        type: STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
        group,
        students: json.result,
        receivedAt: Date.now()
    }
}

export function fetchStudentDisciplineWorkload(studentId) {
    return function (dispatch) {
        dispatch(studentDisciplineWorkloadRequest(group));
        return fetch('http://localhost:8080/api/student/disciplineWorkload?studentId=' + studentId + '&fromDate=2018-01-01&toDate=2018-05-28')
            .then(response => response.json())
            .then(json => dispatch(studentDisciplineWorkloadReceive(group, json)));

    };
}