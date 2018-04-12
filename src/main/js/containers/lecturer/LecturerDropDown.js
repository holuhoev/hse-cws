import {connect} from 'react-redux'
import ObjectDropDown from "../../components/ObjectDropDown";
import {selectLecturer} from "../../actions/lecturer/workloadFilter";
import {changeLecturerSearchQuery} from "../../actions/lecturer/lecturerFilter";
import {fetchLecturers} from "../../actions/lecturer/lecturers";

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
    const {lecturerFilter, workloadFilter, lecturers} = state.lecturerDisciplineWorkload;
    const {lecturer} = workloadFilter;
    const {isFetching, items} = lecturers;
    return {
        initialValue: lecturer,
        options: getOptions(items, "fio"),
        isLoading: isFetching,
        updateOnFilterChange: true,
        filter: lecturerFilter,
        placeHolder: 'Выбрать лектора'
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: function (e, {value}) {
        dispatch(selectLecturer(value));
    },
    loadData: function (filter) {
        const {chair, searchQuery} = filter;
        dispatch(fetchLecturers({chairId: chair, lecturerFio: searchQuery}))
    },
    onSearchChange: function (e, {searchQuery}) {
        dispatch(changeLecturerSearchQuery(searchQuery))
    }
});

const LecturerDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default LecturerDropDown;