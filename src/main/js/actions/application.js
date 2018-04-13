import {SET_ACTIVE_ITEM} from "./actionConsts";

export const setActiveItem = (activeItem) => {
    return {
        type: SET_ACTIVE_ITEM,
        activeItem
    }
};