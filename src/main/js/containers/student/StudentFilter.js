import {connect} from "react-redux";
import ObjectDropDown from "../../components/ObjectDropDown";
import {fetchGroupsIfNeeded, selectGroup} from "../../actions";
import {selectStudent} from "../../actions/students";

const getOptions = (items, renderFieldName) => {
    let options = [];
    if (items) {
        items.forEach(item => {
            options.push({key: item.id, value: item.id, text: item[renderFieldName]})
        });
    }
    return options;
};

const filterGroups = (groups, facultyId) => {
    return (facultyId && groups) ? groups.filter(group => group["facultyId"] === facultyId) : groups;
};

const mapStateToProps = state => {
    const {studentFilter, groups} = state.studentDisciplineWorkload;
    const {group, faculty} = studentFilter;
    return {
        initialValue: group,
        options: getOptions(filterGroups(groups.items, faculty), "number"),
        isLoading: groups.isFetching,
        updateOnFilterChange: false,
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: (e, {value}) => {
        dispatch(selectGroup(value));
        dispatch(selectStudent(undefined))
    },
    loadData: () => {
        dispatch(fetchGroupsIfNeeded())
    }
});

const StudentFilter = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default StudentFilter;