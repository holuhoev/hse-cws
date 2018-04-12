import {connect} from "react-redux";
import DatePickerComponent from "../../components/DatePickerComponent";
import {selectLecturerToDate} from "../../actions/lecturer/workloadFilter";

const mapStateToProps = (state) => {
    const {lecturerDisciplineWorkload} = state;
    const {workloadFilter} = lecturerDisciplineWorkload;
    const {toDate} = workloadFilter;
    return {
        value: toDate
    }
};

const mapDispatchToProps = (dispatch) => ({
    onChange: value => {
        dispatch(selectLecturerToDate(value))
    }
});

export default connect(
    mapStateToProps,
    mapDispatchToProps)
(DatePickerComponent);