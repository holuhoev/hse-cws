import {connect} from "react-redux";
import DatePickerComponent from "../../components/DatePickerComponent";
import {selectLecturerFromDate} from "../../actions/lecturer/workloadFilter";

const mapStateToProps = (state) => {
    const {lecturerDisciplineWorkload} = state;
    const {workloadFilter} = lecturerDisciplineWorkload;
    const {fromDate} = workloadFilter;
    return {
        value: fromDate
    }
};

const mapDispatchToProps = (dispatch) => ({
    onChange: value => {
        dispatch(selectLecturerFromDate(value))
    }
});

export default connect(
    mapStateToProps,
    mapDispatchToProps)
(DatePickerComponent);