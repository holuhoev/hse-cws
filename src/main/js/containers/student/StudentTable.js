import {connect} from 'react-redux'
import React from 'react'
import {Dimmer, Loader, Segment, Table} from 'semantic-ui-react'
import {fetchStudentDisciplineWorkload} from "../../actions/index";

class StudentTable extends React.Component {
    componentDidMount() {
        const {loadData, selectedStudent} = this.props;
        loadData(selectedStudent);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.selectedStudent !== this.props.selectedStudent) {
            const {loadData, selectedStudent} = nextProps;
            loadData(selectedStudent);
        }
    }

    render() {
        const {data, loading} = this.props;
        const headerRow = [
            'Наименование дисциплины',
            'Семинары',
            'Лекции',
            'НИС',
            'Показ работ',
            'Практика',
            'Консультация',
            'Экзамен',
            'Другое'
        ];
        const renderBodyRow = ({name, seminar, lecture, science, practice, exam, workShow, other, consultation}, i) => ({
            key: name || `row-${i}`,
            cells: [
                name || 'No name specified',
                seminar || '',
                lecture || '',
                science || '',
                workShow || '',
                practice || '',
                consultation || '',
                exam || '',
                other || ''

            ],
        });
        return (
            <Segment>
                {loading &&
                <Dimmer active>
                    <Loader size='big'/>
                </Dimmer>
                }
                <Table
                    celled
                    headerRow={headerRow}
                    tableData={data}
                    renderBodyRow={renderBodyRow}
                >
                </Table>
            </Segment>
        )
    }
}

const mapStateToProps = state => {
    const {studentDisciplineWorkload} = state;
    const {workloadFilter, workloads} = studentDisciplineWorkload;

    return {
        loading: workloads.isFetching,
        selectedStudent: workloadFilter.student,
        data: workloads.items
    }
};

const mapDispatchToProps = dispatch => ({
    loadData: (student) => {
        if (student) {
            dispatch(fetchStudentDisciplineWorkload(student))
        }
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(StudentTable);