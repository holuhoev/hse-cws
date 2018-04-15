import {connect} from 'react-redux'
import ObjectDropDown from "../../components/ObjectDropDown";
import {fetchChairsIfNeeded} from "../../actions/lecturer/chairs";
import {changeDepartmentFilter} from "../../actions/department/filter";

const getOptions = (items, renderFieldName) => {
    let options = [];
    if (items) {
        items.forEach(item => {
            options.push({key: item.id, value: item.id, text: item[renderFieldName]})
        });
    }
    return options;
};

const mapStateToProps = state => {
    const {department} = state;
    const {filter} = department;
    const {chairId} = filter;
    const {chairs} = state.lecturer;
    const {isFetching, items} = chairs;
    return {
        initialValue: chairId,
        options: getOptions(items, "name"),
        isLoading: isFetching,
        updateOnFilterChange: false,
        placeHolder: 'Выбрать департамент',
        label: 'Департамент'
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: function (e, {value}) {
        dispatch(changeDepartmentFilter({chairId: value}));
    },
    loadData: function () {
        dispatch(fetchChairsIfNeeded())
    },
    onRemoveButtonClick: (e) => {
        dispatch(changeDepartmentFilter({chairId: undefined}))
    }
});

const ChairDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default ChairDropDown;