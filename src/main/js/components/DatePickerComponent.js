import React from 'react'
import DatePicker from "react-datepicker"
import 'react-datepicker/dist/react-datepicker.css';
import * as style from 'react-datepicker/dist/react-datepicker-cssmodules.css';
const DatePickerComponent = ({onChange, value, isLoading}) => (

    <div style={style}>
        <DatePicker
            selected={value}
            onChange={onChange}
            disabled={isLoading}
            locale="ru"
            dateFormat="LL"/>
    </div>

);

export default DatePickerComponent;