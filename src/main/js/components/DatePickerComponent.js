import React from 'react'
import DatePicker from "react-datepicker"
import 'react-datepicker/dist/react-datepicker.css';

const DatePickerComponent = ({onChange, value}) => (
    <DatePicker
        selected={value}
        onChange={onChange}
        locale="ru"/>
);

export default DatePickerComponent;