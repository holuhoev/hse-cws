import {connect} from "react-redux";
import ObjectDropDown from "../../components/ObjectDropDown";

import {selectStudent} from "../../actions/student/students";
import {changeStudentFilter} from "../../actions/student/studentFilter";
import {fetchInstitutesIfNeeded} from "../../actions/student/institutes";

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
    const {studentFilter, institutes} = state.studentDisciplineWorkload;
    const {institute} = studentFilter;
    return {
        initialValue: institute,
        options: getOptions(institutes.items, "name"),
        isLoading: institutes.isFetching,
        updateOnFilterChange: false,
        placeHolder: 'Выбрать факультет',
        label: 'Факультет'
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: (e, {value}) => {
        dispatch(changeStudentFilter({instituteId: value, facultyId: undefined, groupId: undefined, studentFio: ''}));
        dispatch(selectStudent(undefined));
    },
    loadData: () => {
        dispatch(fetchInstitutesIfNeeded())
    },
    onRemoveButtonClick: (e) => {
        dispatch(changeStudentFilter({instituteId: undefined, facultyId: undefined, groupId: undefined, studentFio: ''}))
        dispatch(selectStudent(undefined));
    }
});

const InstituteDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default InstituteDropDown;