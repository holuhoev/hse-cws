import {connect} from 'react-redux'
import ObjectDropDown from "../../components/ObjectDropDown";
import {changeSearchString, fetchStudents, selectStudent} from "../../actions/student/students";
import {changeStudentFilter} from "../../actions/student/studentFilter";

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
    const {studentFilter, workloadFilter, students} = state.student;
    const {studentId} = workloadFilter;
    const {isFetching, items} = students;
    return {
        initialValue: studentId,
        options: getOptions(items, "fio"),
        // isLoading: isFetching,
        updateOnFilterChange: true,
        filter: studentFilter,
        label: 'Студент',
        placeHolder: 'Выбрать студента'
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: function (e, {value}) {
        dispatch(selectStudent(value));
    },
    loadData: function (filter) {
        dispatch(fetchStudents(filter))
    },
    onSearchChange: function (e, {searchQuery}) {
        dispatch(changeStudentFilter({studentFio: searchQuery}))
    },
    onRemoveButtonClick: (e) => {
        dispatch(selectStudent(undefined));
        dispatch(changeStudentFilter({studentFio: undefined}))
    }
});

const StudentDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default StudentDropDown;