import {connect} from "react-redux";
import {selectStudentToDate} from "../../actions/student/dates";
import DatePickerComponent from "../../components/DatePickerComponent";

const mapStateToProps = (state) => {
    const {studentDisciplineWorkload} = state;
    const {workloadFilter} = studentDisciplineWorkload;
    const {toDate} = workloadFilter;
    return {
        value: toDate
    }
};

const mapDispatchToProps = (dispatch) => ({
    onChange: value => {
        dispatch(selectStudentToDate(value))
    }
});

export default connect(
    mapStateToProps,
    mapDispatchToProps)
(DatePickerComponent);