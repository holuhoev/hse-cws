import React from 'react'
import DatePicker from "react-datepicker"
import 'react-datepicker/dist/react-datepicker.css';
import 'react-datepicker/dist/react-datepicker-cssmodules.css';

const DatePickerComponent = ({onChange, value}) => (
    <DatePicker
        selected={value}
        onChange={onChange}
        locale="ru"
        dateFormat="LL"/>
);

export default DatePickerComponent;