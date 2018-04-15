import {connect} from "react-redux";
import ObjectDropDown from "../../components/ObjectDropDown";
import {fetchGroupsIfNeeded} from "../../actions";
import {selectStudent} from "../../actions/student/students";
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
        placeHolder: 'Выбрать группу',
        label: 'Группа'
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: (e, {value}) => {
        dispatch(changeStudentFilter({groupId: value, studentFio: undefined}));
        dispatch(selectStudent(undefined));
    },
    loadData: () => {
        dispatch(fetchGroupsIfNeeded())
    },
    onRemoveButtonClick: (e) => {
        dispatch(changeStudentFilter({groupId: undefined, studentFio: undefined}));
        dispatch(selectStudent(undefined));
    }
});

const GroupDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default GroupDropDown;