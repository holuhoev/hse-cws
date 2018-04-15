import {connect} from "react-redux";
import DatePickerComponent from "../../components/DatePickerComponent";
import {changeDepartmentFilter} from "../../actions/department/filter";

const mapStateToProps = (state) => {
    const {department} = state;
    const {filter} = department;
    const {toDate} = filter;
    return {
        value: toDate
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