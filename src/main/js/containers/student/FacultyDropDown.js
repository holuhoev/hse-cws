import {connect} from "react-redux";
import ObjectDropDown from "../../components/ObjectDropDown";
import {selectStudent} from "../../actions/student/students";
import {changeStudentFilter} from "../../actions/student/studentFilter";
import {fetchFacultiesIfNeeded} from "../../actions/student/faculties";

const getOptions = (items, renderFieldName) => {
    let options = [];
    if (items) {
        items.forEach(item => {
            options.push({key: item.id, value: item.id, text: item[renderFieldName]})
        });
    }
    return options;
};


const filterFaculties = (items, instituteId) => {
    return (instituteId && items) ? items.filter(group => group["instituteId"] === instituteId) : items;
};

const mapStateToProps = state => {
    const {studentFilter, faculties} = state.studentDisciplineWorkload;
    const {facultyId, instituteId} = studentFilter;
    return {
        initialValue: facultyId,
        options: getOptions(filterFaculties(faculties.items, instituteId), "name"),
        isLoading: faculties.isFetching,
        updateOnFilterChange: false,
        placeHolder: 'Выбрать программу',
        label: 'Образовательная программа'
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: (e, {value}) => {
        dispatch(changeStudentFilter({facultyId: value, groupId: undefined, studentFio: undefined}));
        dispatch(selectStudent(undefined));
    },
    loadData: () => {
        dispatch(fetchFacultiesIfNeeded())
    },
    onRemoveButtonClick: (e) => {
        dispatch(changeStudentFilter({facultyId: undefined, groupId: undefined, studentFio: undefined}));
        dispatch(selectStudent(undefined));
    }
});

const FacultyDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default FacultyDropDown;