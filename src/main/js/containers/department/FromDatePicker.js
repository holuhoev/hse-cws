import {connect} from "react-redux";
import DatePickerComponent from "../../components/DatePickerComponent";
import {changeDepartmentFilter} from "../../actions/department/filter";

const mapStateToProps = (state) => {
    const {department} = state;
    const {filter, workloads} = department;
    const {fromDate} = filter;
    const {isFetching} = workloads;
    return {
        value: fromDate,
        disabled: isFetching
    }
};

const mapDispatchToProps = (dispatch) => ({
    onChange: value => {
        dispatch(changeDepartmentFilter({fromDate: value}))
    }
});

export default connect(
    mapStateToProps,
    mapDispatchToProps)
(DatePickerComponent);