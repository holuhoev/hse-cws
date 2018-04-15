import {connect} from "react-redux";
import DatePickerComponent from "../../components/DatePickerComponent";
import {changeDepartmentFilter} from "../../actions/department/filter";

const mapStateToProps = (state) => {
    const {department} = state;
    const {filter, workloads} = department;
    const {toDate} = filter;
    const {isFetching} = workloads;
    return {
        value: toDate,
        disabled: isFetching
    }
};

const mapDispatchToProps = (dispatch) => ({
    onChange: value => {
        dispatch(changeDepartmentFilter({toDate: value}))
    }
});

export default connect(
    mapStateToProps,
    mapDispatchToProps)
(DatePickerComponent);