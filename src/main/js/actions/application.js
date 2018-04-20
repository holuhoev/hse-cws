import {CHANGE_APP_STATE} from "./actionConsts";

export const changeAppState = (value) => {
    return {
        type: CHANGE_APP_STATE,
        value
    }
};