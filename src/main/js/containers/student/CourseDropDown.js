import {connect} from "react-redux";
import ObjectDropDown from "../../components/ObjectDropDown";
import {selectStudent} from "../../actions/student/students";
import {changeStudentFilter} from "../../actions/student/studentFilter";

const options = [
    {key: 1, value: 'FIRST', text: 'Первый'},
    {key: 2, value: 'SECOND', text: 'Второй'},
    {key: 3, value: 'THIRD', text: 'Третий'},
    {key: 4, value: 'FOURTH', text: 'Четвертый'},
    {key: 5, value: 'FIFTH', text: 'Пятый'}
];

const mapStateToProps = state => {
    const {studentFilter} = state.studentDisciplineWorkload;
    const {course} = studentFilter;
    return {
        initialValue: course,
        updateOnFilterChange: false,
        placeHolder: 'Выбрать курс',
        label: 'Курс обучения',
        options
    }
};


const mapDispatchToProps = dispatch => ({
    onChange: (e, {value}) => {
        dispatch(changeStudentFilter({course: value, groupId: undefined, studentFio: undefined}));
        dispatch(selectStudent(undefined));
    },
    onRemoveButtonClick: (e) => {
        dispatch(changeStudentFilter({course: undefined, groupId: undefined, studentFio: undefined}));
        dispatch(selectStudent(undefined));
    }
});

const CourseDropDown = connect(
    mapStateToProps,
    mapDispatchToProps
)(ObjectDropDown);

export default CourseDropDown;