import fetch from 'isomorphic-fetch'

// TODO: other entities & decompose
export const SELECT_GROUP = 'SELECT GROUP';

export function selectGroup(group) {
    return {
        type: SELECT_GROUP,
        group
    }
}

export const STUDENTS_REQUEST = 'STUDENTS_REQUEST';
export const STUDENTS_RECEIVE = 'STUDENTS_RECEIVE';
export const SELECT_STUDENT = 'SELECT_STUDENT';


function normalize(data = []) {
    let result = {};
    data.forEach((value => {
        result[value.id] = value;
    }));
}

export function studentsRequest() {
    return {
        type: STUDENTS_REQUEST
    }
}

export function studentsReceive(json) {
    return {
        type: STUDENTS_RECEIVE,
        data: json.result,
        receivedAt: Date.now()
    }
}

export function fetchStudents() {
    return function (dispatch) {
        dispatch(studentsRequest());
        return fetch('http://localhost:8080/api/student/getAll')
            .then(response => response.json())
            .then(json => dispatch(studentsReceive(json)));

    };
}

export function selectStudent(student) {
    return {
        type: SELECT_STUDENT,
        student
    }
}

export const STUDENT_DISCIPLINE_WORKLOAD_REQUEST = 'STUDENT_DISCIPLINE_WORKLOAD_REQUEST';
export const STUDENT_DISCIPLINE_WORKLOAD_RECEIVE = 'STUDENT_DISCIPLINE_WORKLOAD_RECEIVE';

export function studentDisciplineWorkloadRequest(student) {
    return {
        type: STUDENT_DISCIPLINE_WORKLOAD_REQUEST,
        student
    }
}

export function studentDisciplineWorkloadReceive(student, json) {
    return {
        type: STUDENT_DISCIPLINE_WORKLOAD_RECEIVE,
        student,
        data: json.result,
        receivedAt: Date.now()
    }
}

export function fetchStudentDisciplineWorkload(student) {
    return function (dispatch) {
        dispatch(studentDisciplineWorkloadRequest(student));
        return fetch('http://localhost:8080/api/student/disciplineWorkload?studentId=' + student + '&fromDate=2018-01-01&toDate=2018-05-28')
            .then(response => response.json())
            .then(json => dispatch(studentDisciplineWorkloadReceive(student, json)));

    };
}