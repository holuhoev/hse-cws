import {connect} from 'react-redux'
import ObjectDropDown from "../../components/ObjectDropDown";
import {selectLecturer} from "../../actions/lecturer/workloadFilter";
import {changeLecturerFilter} from "../../actions/lecturer/lecturerFilter";
import {fetchLecturers} from "../../actions/lecturer/lecturers";
import {changeStudentFilter} from "../../actions/student/studentFilter";
import {selectStudent} from "../../actions/student/students";

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
    const {lecturerFilter, workloadFilter, lecturers} = state.lecturer;
    const {lecturer} = workloadFilter;
    const {isFetching, items} = lecturers;
    return {
        initialValue: lecturer,
        options: getOptions(items, "fio"),
        isLoading: isFetching,
        updateOnFilterChange: true,
        filter: lecturerFilter,
        placeHolder: 'Выбрать преподавателя',
        label: 'Преподаватель'
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: function (e, {value}) {
        dispatch(selectLecturer(value));
    },
    loadData: function (filter) {
        dispatch(fetchLecturers(filter))
    },
    onSearchChange: function (e, {searchQuery}) {
        dispatch(changeLecturerFilter({lecturerFio: searchQuery}))
    },
    onRemoveButtonClick: (e) => {
        dispatch(selectLecturer(undefined));
        dispatch(changeLecturerFilter({lecturerFio: undefined}))
    }
});

const LecturerDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default LecturerDropDown;