import {connect} from "react-redux";
import {selectStudentFromDate} from "../../actions/student/dates";
import DatePickerComponent from "../../components/DatePickerComponent";

const mapStateToProps = (state) => {
    const {student} = state;
    const {workloadFilter} = student;
    const {fromDate} = workloadFilter;
    return {
        value: fromDate
    }
};

const mapDispatchToProps = (dispatch) => ({
    onChange: value => {
        dispatch(selectStudentFromDate(value))
    }
});

export default connect(
    mapStateToProps,
    mapDispatchToProps)
(DatePickerComponent);