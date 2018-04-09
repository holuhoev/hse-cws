import {connect} from 'react-redux'
import React from 'react'
import {Dimmer, Loader, Segment, Table, Sticky} from 'semantic-ui-react'
import {fetchStudentDisciplineWorkload} from "../actions";

class StudentTable extends React.Component {
    componentDidMount() {
        const {loadData, selectedStudent} = this.props;
        loadData(selectedStudent);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.selectedStudent !== this.props.selectedStudent) {
            const {loadData, selectedStudent} = nextProps
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
                    // tableData={data}
                    // renderBodyRow={renderBodyRow}
                >

                    <Table.Header>
                        <Sticky active>
                            <Table.Row>
                                <Table.HeaderCell>Name</Table.HeaderCell>
                                <Table.HeaderCell>Registration Date</Table.HeaderCell>
                                <Table.HeaderCell>E-mail address</Table.HeaderCell>
                                <Table.HeaderCell>Premium Plan</Table.HeaderCell>
                            </Table.Row>
                        </Sticky>
                    </Table.Header>

                    <Table.Body>
                        <Table.Row>
                            <Table.Cell>John Lilkidasdasdasdasdda</Table.Cell>
                            <Table.Cell>September 14, 2013</Table.Cell>
                            <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                            <Table.Cell>No</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jamie Harington</Table.Cell>
                            <Table.Cell>January 11, 2014</Table.Cell>
                            <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jill Lewis</Table.Cell>
                            <Table.Cell>May 11, 2014</Table.Cell>
                            <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>John Lilki</Table.Cell>
                            <Table.Cell>September 14, 2013</Table.Cell>
                            <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                            <Table.Cell>No</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jamie Harington</Table.Cell>
                            <Table.Cell>January 11, 2014</Table.Cell>
                            <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jill Lewis</Table.Cell>
                            <Table.Cell>May 11, 2014</Table.Cell>
                            <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>John Killer</Table.Cell>
                            <Table.Cell>September 14, 2013</Table.Cell>
                            <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                            <Table.Cell>No</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jamie Harington</Table.Cell>
                            <Table.Cell>January 11, 2014</Table.Cell>
                            <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jill Lewis</Table.Cell>
                            <Table.Cell>May 11, 2014</Table.Cell>
                            <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>John Lilki</Table.Cell>
                            <Table.Cell>September 14, 2013</Table.Cell>
                            <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                            <Table.Cell>No</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jamie Harington</Table.Cell>
                            <Table.Cell>January 11, 2014</Table.Cell>
                            <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jill Lewis</Table.Cell>
                            <Table.Cell>May 11, 2014</Table.Cell>
                            <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>John Lilki</Table.Cell>
                            <Table.Cell>September 14, 2013</Table.Cell>
                            <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                            <Table.Cell>No</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jamie Harington</Table.Cell>
                            <Table.Cell>January 11, 2014</Table.Cell>
                            <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jill Lewis</Table.Cell>
                            <Table.Cell>May 11, 2014</Table.Cell>
                            <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>John Lilki</Table.Cell>
                            <Table.Cell>September 14, 2013</Table.Cell>
                            <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                            <Table.Cell>No</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jamie Harington</Table.Cell>
                            <Table.Cell>January 11, 2014</Table.Cell>
                            <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jill Lewis</Table.Cell>
                            <Table.Cell>May 11, 2014</Table.Cell>
                            <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>John Lilki</Table.Cell>
                            <Table.Cell>September 14, 2013</Table.Cell>
                            <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                            <Table.Cell>No</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jamie Harington</Table.Cell>
                            <Table.Cell>January 11, 2014</Table.Cell>
                            <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jill Lewis</Table.Cell>
                            <Table.Cell>May 11, 2014</Table.Cell>
                            <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>John Lilki</Table.Cell>
                            <Table.Cell>September 14, 2013</Table.Cell>
                            <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                            <Table.Cell>No</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jamie Harington</Table.Cell>
                            <Table.Cell>January 11, 2014</Table.Cell>
                            <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Jill Lewis</Table.Cell>
                            <Table.Cell>May 11, 2014</Table.Cell>
                            <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                            <Table.Cell>Yes</Table.Cell>
                        </Table.Row>
                    </Table.Body>
                </Table>
            </Segment>
        )
    }
}

const mapStateToProps = state => {
    const {studentDisciplineWorkload} = state;
    const {filter, workloads} = studentDisciplineWorkload;

    return {
        loading: workloads.isFetching,
        selectedStudent: filter.selectedStudent,
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