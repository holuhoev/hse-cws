import React from 'react'
import DatePicker from "react-datepicker"
import 'react-datepicker/dist/react-datepicker.css';
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import {Segment} from 'semantic-ui-react'

const DatePickerComponent = ({onChange, value, isLoading}) => (
    <DatePicker
        selected={value}
        onChange={onChange}
        disabled={isLoading}
        locale="ru"
        dateFormat="LL"/>
);

export default DatePickerComponent;