import React from 'react'
import DatePicker from "react-datepicker"
import 'react-datepicker/dist/react-datepicker.css';
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import {Segment} from 'semantic-ui-react'

const DatePickerComponent = ({onChange, value}) => (
    <Segment>
        <DatePicker
            selected={value}
            onChange={onChange}
            locale="ru"
            dateFormat="LL"/>
    </Segment>

);

export default DatePickerComponent;