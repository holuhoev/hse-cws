import React, {Component} from 'react'
import PropTypes from 'prop-types'
import DisciplineWorkloads from "../components/DisciplineWorkloads"
import {connect} from 'react-redux'
import {fetchStudentDisciplineWorkload, fetchStudents} from "../actions";
import { Dropdown } from 'semantic-ui-react'

class App extends Component {
    static propTypes = {
        student: PropTypes.number.isRequired,
        students: PropTypes.shape({
            isFetching: PropTypes.bool.isRequired,
            items: PropTypes.array.isRequired
        }),
        isFetching: PropTypes.bool.isRequired,
        items: PropTypes.array.isRequired,
        dispatch: PropTypes.func.isRequired
    };

    componentWillMount() {
        const {dispatch, student} = this.props;
        dispatch(fetchStudents(6826));
        if (!!student) {
            dispatch(fetchStudentDisciplineWorkload(student))
        }
    }

    onStudentSelect(student) {
        debugger;
    }


    render() {
        const options = [
            { key: 'a', value: 'a', text: 'UPPERCASE' },
            { key: 'b', value: 'b', text: 'lowercase' },
        ];
        const {items, isFetching, student, students} = this.props;
        const isEmpty = items.length === 0;
        return (
            <div>
                <Dropdown
                    fluid
                    options={options}
                    placeholder={'Try to search for case or CASE'}
                    selection
                />
                {isEmpty
                    ? (isFetching ? "Загрузка" : "Нет данных")
                    : <DisciplineWorkloads items={items}/>}
            </div>
        )
    }
}

const mapStateToProps = state => {
    console.log(state);
    const {studentDisciplineWorkload} = state;
    const {filter, workloads, students} = studentDisciplineWorkload;

    return {
        student: filter.selectedStudent,
        students,
        isFetching: workloads.isFetching,
        items: workloads.items
    }
};

export default connect(mapStateToProps)(App)
