import {connect} from 'react-redux'
import React from 'react'
import {Dimmer, Loader, Segment, Table, Divider, Message} from 'semantic-ui-react'
import {fetchStudentDisciplineWorkload} from "../../actions/index";

class StudentTable extends React.Component {
    componentDidMount() {
        const {loadData, filter} = this.props;
        loadData(filter);
    }

    componentWillReceiveProps(nextProps) {
        const {filter} = this.props;
        if (filter && this.notEqual(filter, nextProps.filter)) {
            const {loadData} = nextProps;
            loadData(nextProps.filter)
        }
    }

    notEqual(obj1, obj2) {
        let isEqual = false;
        Object.keys(obj1).forEach(key => {
            if (obj1[key] !== obj2[key]) {
                isEqual = true;
            }
        });
        return isEqual;
    }

    render() {
        const {data, loading, filter} = this.props;
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
                <Dimmer active inverted>
                    <Loader size='big' content='Загрузка данных...'/>
                </Dimmer>
                }
                <Table
                    celled
                    headerRow={headerRow}
                    tableData={data}
                    renderBodyRow={renderBodyRow}
                >
                </Table>
                {data && data.length > 0 ? <p></p>
                    :
                    <Divider horizontal>
                        <Message size='small' positive>
                            <p> {filter["selectedStudent"] ? 'По данному студенту нет данных.' : 'Выберите студента'}</p>
                        </Message>
                    </Divider>
                }
            </Segment>
        )
    }
}

const mapStateToProps = state => {
    const {studentDisciplineWorkload} = state;
    const {workloadFilter, workloads} = studentDisciplineWorkload;
    return {
        loading: workloads.isFetching,
        data: workloads.items,
        filter: workloadFilter
    }
};

const mapDispatchToProps = dispatch => ({
    loadData: (filter) => {
        dispatch(fetchStudentDisciplineWorkload(filter))
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(StudentTable);