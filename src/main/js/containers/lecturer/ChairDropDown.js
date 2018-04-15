import {connect} from 'react-redux'
import ObjectDropDown from "../../components/ObjectDropDown";
import {changeLecturerFilter} from "../../actions/lecturer/lecturerFilter";
import {fetchChairsIfNeeded} from "../../actions/lecturer/chairs";

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
    const {lecturerFilter, chairs} = state.lecturerDisciplineWorkload;
    const {chairId} = lecturerFilter;
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
        dispatch(changeLecturerFilter({chairId: value, lecturerFio: undefined}));
    },
    loadData: function () {
        dispatch(fetchChairsIfNeeded())
    },
    onRemoveButtonClick: (e) => {
        dispatch(changeLecturerFilter({chairId: undefined, lecturerFio: undefined}))
    }
});

const ChairDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default ChairDropDown;