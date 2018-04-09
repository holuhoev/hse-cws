import React from 'react'
import {Dropdown} from 'semantic-ui-react'

const ObjectDropDown = ({initialValue, options, isLoading, onChange, onSearchChange}) => (
    <Dropdown
        options={options}
        placeholder={'Выберите значение...'}
        selection
        search
        value={initialValue}
        loading={isLoading}
        disabled={isLoading}
        onChange={onChange}
        onSearchChange={onSearchChange}
    />
);


export default ObjectDropDown;
