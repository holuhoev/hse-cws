import {connect} from 'react-redux'
import ObjectDropDown from "../components/ObjectDropDown";
import {changeSearchString, fetchStudents, selectStudent} from "../actions/students";

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
        isLoading: isFetching,
        updateOnFilterChange: true,
        filter
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: function (e, {value}) {
        dispatch(selectStudent(value));
    },
    loadData: function (filter) {
        const {selectedGroup, searchString} = filter;
        dispatch(fetchStudents({groupId: selectedGroup, studentFio: searchString}))
    },
    onSearchChange: function (e, {searchQuery}) {
        dispatch(changeSearchString(searchQuery))
    }
});

const StudentFilter = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default StudentFilter;