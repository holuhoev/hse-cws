import thunkMiddleware from 'redux-thunk'
import {createLogger} from 'redux-logger'
import {createStore, applyMiddleware} from 'redux'
import {selectGroup, fetchStudents} from './actions'

import rootReducer from './reducers'

const loggerMiddleware = createLogger();

const store = createStore(
    rootReducer,
    applyMiddleware(
        thunkMiddleware, // позволяет нам отправлять функции
        loggerMiddleware // аккуратно логируем действия
    )
);

store.dispatch(selectGroup({id: 6395, name: "БПИ154"}));
store.dispatch(fetchStudents({id: 6395, name: "БПИ154"}))
    .then(() =>
        console.log(store.getState())
    );