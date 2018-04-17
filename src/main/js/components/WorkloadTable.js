import React from 'react'
import {Dimmer, Loader, Segment, Table, Divider, Message} from 'semantic-ui-react'

class WorkloadTable extends React.Component {
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
        let isEqual = true;
        Object.keys(obj1).forEach(key => {
            if (obj1[key] !== obj2[key]) {
                isEqual = false;
            }
        });
        return !isEqual;
    }

    formatWorkloadColumn(value) {
        return value && value > 0 ? value : '-'
    }

    render() {
        const {data, loading} = this.props;

        let index = 0;
        return (
            <Segment>
                {loading &&
                <Dimmer active inverted>
                    <Loader size='big' content='Загрузка данных...'/>
                </Dimmer>
                }
                <Table celled striped>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell>Наименование дисциплины</Table.HeaderCell>
                            <Table.HeaderCell>Семинары</Table.HeaderCell>
                            <Table.HeaderCell>Лекции</Table.HeaderCell>
                            <Table.HeaderCell>НИС</Table.HeaderCell>
                            <Table.HeaderCell>Практика</Table.HeaderCell>
                            <Table.HeaderCell>Показ работ</Table.HeaderCell>
                            <Table.HeaderCell>Консультация</Table.HeaderCell>
                            <Table.HeaderCell>Экзамен</Table.HeaderCell>
                            <Table.HeaderCell>Контрольная работа</Table.HeaderCell>
                            <Table.HeaderCell>Другое</Table.HeaderCell>
                            <Table.HeaderCell>Сумма</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        {
                            data ?
                                data.map(({name, seminar, lecture, science, practice, exam, workShow, other, consultation, test,total}) => {
                                    return <Table.Row key={index++}>
                                        <Table.Cell>{name}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(seminar)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(lecture)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(science)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(practice)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(workShow)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(consultation)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(exam)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(test)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(other)}</Table.Cell>
                                        <Table.Cell>{this.formatWorkloadColumn(total)}</Table.Cell>
                                    </Table.Row>
                                })
                                : <div></div>
                        }
                    </Table.Body>
                </Table>
                {data && data.length > 0 ? <div></div>
                    :
                    <Divider horizontal>
                        <Message size='small' positive>
                            <p> {'Нет данных, по выбранным параметрам'}</p>
                        </Message>
                    </Divider>
                }
            </Segment>
        )
    }
}

export default WorkloadTable;