import {connect} from 'react-redux'
import {fetchDepartmentWorkload, updateDepartmentWorkload} from "../../actions/department/workload";
import React from "react";
import {Dimmer, Divider, Loader, Message, Segment, Table, Accordion, Icon, Input} from "semantic-ui-react";
import {changeAppState} from "../../actions/application";
import {changeDepartmentTableFilter} from "../../actions/department/tableFilter";
import _ from 'lodash'

class SumWorkloadTable extends React.Component {
    componentDidMount() {
        const {loadData, filter, items} = this.props;
        if (!items || items.length < 1) {
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

    handleSort = clickedColumn => () => {
        const {column, items, direction, updateWorkload} = this.props;

        if (column !== clickedColumn) {
            updateWorkload({
                column: clickedColumn,
                items: _.sortBy(items, [clickedColumn]),
                direction: 'ascending',
            });

            return
        }

        updateWorkload({
            items: items.reverse(),
            direction: direction === 'ascending' ? 'descending' : 'ascending',
        });
    };

    handleCellClick = lecturerId => () => {

    };

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
        const {items, loading, showFilter, onShowFilterClick, changeTableFilter, column, direction} = this.props;
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
                            <Table.HeaderCell sorted={column === 'fio' ? direction : null}
                                              onClick={this.handleSort('fio')}>
                                Преподаватель
                            </Table.HeaderCell>
                            <Table.HeaderCell sorted={column === 'workload' ? direction : null}
                                              onClick={this.handleSort('workload')}>
                                Часы
                            </Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        {
                            items ?
                                items.map(({fio, workload}) => {
                                    return <Table.Row key={index++}>
                                        <Table.Cell>{fio}</Table.Cell>
                                        <Table.Cell>{workload}</Table.Cell>
                                    </Table.Row>
                                })
                                : <div></div>
                        }
                    </Table.Body>
                </Table>
                {items && items.length > 0 ? <div></div>
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
    const {isFetching, column, direction, items} = workloads;
    const {showTableFilter} = application;
    const {searchString} = tableFilter;
    return {
        loading: isFetching,
        items: visibleItems(items, searchString),
        filter,
        column,
        direction,
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
    },
    updateWorkload: (value) => {
        dispatch(updateDepartmentWorkload(value))
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SumWorkloadTable);