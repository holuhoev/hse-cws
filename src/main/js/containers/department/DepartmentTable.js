import {connect} from 'react-redux'
import {fetchDepartmentWorkload} from "../../actions/department/workload";
import React from "react";
import {Dimmer, Divider, Loader, Message, Segment, Table, Accordion, Icon, Input} from "semantic-ui-react";
import {changeAppState} from "../../actions/application";
import {changeDepartmentTableFilter} from "../../actions/department/tableFilter";

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
        const {data, loading, showFilter, onShowFilterClick, changeTableFilter} = this.props;
        let index = 0;
        return (
            <Segment>
                {loading &&
                <Dimmer active inverted>
                    <Loader size='big' content='Загрузка данных...'/>
                </Dimmer>
                }
                <Accordion styled>
                    <Accordion.Title active={showFilter} index={0} onClick={() => onShowFilterClick(showFilter)}>
                        <Icon name='dropdown'/>
                        Показать фильтр
                    </Accordion.Title>
                    <Accordion.Content active={showFilter}>
                        <Input label='Преподаватель' placeholder='ФИО...' icon='search' onChange={changeTableFilter}/>
                    </Accordion.Content>
                </Accordion>
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


const visibleItems = (items, searchString) => {
    return searchString && searchString.length > 0
        ? items.filter(item => item["fio"].toLowerCase().indexOf(searchString.trim().toLowerCase()) !== -1)
        : items
};

const mapStateToProps = state => {
    const {department, application} = state;
    const {filter, workloads, tableFilter} = department;
    const {showTableFilter} = application;
    const {searchString} = tableFilter;
    return {
        loading: workloads.isFetching,
        data: visibleItems(workloads.items, searchString),
        filter,
        showFilter: showTableFilter
    }
};

const mapDispatchToProps = dispatch => ({
    loadData: (filter) => {
        dispatch(fetchDepartmentWorkload(filter))
    },
    onShowFilterClick: (currentShowFilterState) => {
        dispatch(changeAppState({showTableFilter: !currentShowFilterState}))
    },
    changeTableFilter: (e, data) => {
        dispatch(changeDepartmentTableFilter({searchString: data.value}))
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SumWorkloadTable);