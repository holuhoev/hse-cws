import fetch from 'isomorphic-fetch'

// Тип действия, отвечающий за выбор группы студентов пользователем.
export const SELECT_GROUP = 'SELECT GROUP';
// Тип действия, который отвечает за обновление данных в группе.
export const INVALIDATE_GROUP = 'INVALIDATE_GROUP';


// генератор действия выбора группы.
export function selectGroup(group) {
    return {
        type: SELECT_GROUP,
        group
    }
}

export function invalidateGroup(group) {
    return {
        type: INVALIDATE_GROUP,
        group
    }
}


// Тип действия, отвечающий за фетч данных
export const STUDENTS_SUM_WORKLOAD_REQUEST = 'STUDENTS_SUM_WORKLOAD_REQUEST';
export const STUDENTS_SUM_WORKLOAD_RECEIVE = 'STUDENTS_SUM_WORKLOAD_RECEIVE';


// Когда надо будет фетчить данные о загрузке загруженностей студентов для группы,
// посылаем действие STUDENTS_SUM_WORKLOAD_REQUEST.
// TODO: 1. Добавить fromDate, toDate.
export function requestStudentsSumWorkload(group) {
    return {
        type: STUDENTS_SUM_WORKLOAD_REQUEST,
        group
    }
}

// Когда сетевой запрос будет осуществлен, мы отправим действие STUDENTS_SUM_WORKLOAD_RECEIVE
// TODO: 1. Добавить fromDate, toDate.
export function studentsSumWorkloadReceive(group, json) {
    return {
        type: STUDENTS_SUM_WORKLOAD_RECEIVE,
        group,
        students: json.result,
        receivedAt: Date.now()
    }
}

export function fetchStudents(group) {
    return function (dispatch) {
        // Первая отправка: состояние приложения обновлено,
        // чтобы сообщить, что запускается вызов API.
        dispatch(requestStudentsSumWorkload(group));

        return fetch('http://localhost:8080/api/student/sumWorkload?groupId=' + group.id + '&fromDate=2018-03-01&toDate=2018-03-28')
            .then(response => response.json())
            .then(json => dispatch(studentsSumWorkloadReceive(group,json)));

    };
}