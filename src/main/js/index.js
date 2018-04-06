import thunkMiddleware from 'redux-thunk'
import {createLogger} from 'redux-logger'
import {createStore, applyMiddleware} from 'redux'
import {selectGroup, fetchStudents, selectStudent, fetchStudentDisciplineWorkload} from './actions'

import rootReducer from './reducers'

const loggerMiddleware = createLogger();

const store = createStore(
    rootReducer,
    applyMiddleware(
        thunkMiddleware,
        loggerMiddleware
    )
);

store.dispatch(selectGroup(6395));
store.dispatch(fetchStudents())
    .then(() =>
        console.log(store.getState())
    );
store.dispatch(selectStudent(83967));
store.dispatch(fetchStudentDisciplineWorkload(83967));