import {connect} from 'react-redux'
import {fetchDepartmentWorkload} from "../../actions/department/workload";
import React from "react";
import {Dimmer, Divider, Loader, Message, Segment, Table} from "semantic-ui-react";

class SumWorkloadTable extends React.Component {
    componentDidMount() {
        const {loadData, filter, data} = this.props;
        if (!data || data.length < 1) {
            loadData(filter);
        }
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
                <Table celled striped sortable>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell>Преподаватель</Table.HeaderCell>
                            <Table.HeaderCell>Часы</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        {
                            data ?
                                data.map(({fio, workload}) => {
                                    return <Table.Row key={index++}>
                                        <Table.Cell>{fio}</Table.Cell>
                                        <Table.Cell>{workload}</Table.Cell>
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

const mapStateToProps = state => {
    const {department} = state;
    const {filter, workloads} = department;
    return {
        loading: workloads.isFetching,
        data: workloads.items,
        filter
    }
};

const mapDispatchToProps = dispatch => ({
    loadData: (filter) => {
        dispatch(fetchDepartmentWorkload(filter))
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SumWorkloadTable);