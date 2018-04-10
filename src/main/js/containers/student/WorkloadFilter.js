import {connect} from 'react-redux'
import ObjectDropDown from "../../components/ObjectDropDown";
import {changeSearchString, fetchStudents, selectStudent} from "../../actions/students";

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
    const {studentFilter, workloadFilter, students} = state.studentDisciplineWorkload;
    const {student} = workloadFilter;
    const {isFetching, items} = students;
    return {
        initialValue: student,
        options: getOptions(items, "fio"),
        isLoading: isFetching,
        updateOnFilterChange: true,
        filter: studentFilter
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: function (e, {value}) {
        dispatch(selectStudent(value));
    },
    loadData: function (filter) {
        const {group, searchQuery} = filter;
        dispatch(fetchStudents({groupId: group, studentFio: searchQuery}))
    },
    onSearchChange: function (e, {searchQuery}) {
        dispatch(changeSearchString(searchQuery))
    }
});

const WorkloadFilter = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default WorkloadFilter;