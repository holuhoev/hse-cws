import 'semantic-ui-css/semantic.min.css';
import thunkMiddleware from 'redux-thunk'
import {createLogger} from 'redux-logger'
import {createStore, applyMiddleware} from 'redux'
import {Provider} from 'react-redux'
import rootReducer from './reducers'
import App from "./components/App";
import React from "react";
import {render} from 'react-dom'

const loggerMiddleware = createLogger();

const store = createStore(
    rootReducer,
    applyMiddleware(
        thunkMiddleware,
        loggerMiddleware
    )
);

render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);