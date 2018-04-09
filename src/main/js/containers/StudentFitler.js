import {connect} from 'react-redux'
import ObjectDropDown from "../components/ObjectDropDown";
import {fetchStudentDisciplineWorkload, fetchStudents, selectStudent} from "../actions";

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
    const {filter, students} = state.studentDisciplineWorkload;
    const {selectedStudent} = filter;
    const {isFetching, items} = students;
    return {
        initialValue: selectedStudent,
        options: getOptions(items, "fio"),
        isLoading: isFetching
    }
};

const mapDispatchToProps = dispatch => ({
    onChange: function (e, {value}) {
        dispatch(selectStudent(value));
    }
});

const StudentFilter = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default StudentFilter;