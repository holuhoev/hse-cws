import React, {Component} from 'react'
import {Dropdown} from 'semantic-ui-react'


class ObjectDropDown extends Component {
    render() {
        const {initialValue, options, isLoading, onChange, onSearchChange} = this.props;
        return (<Dropdown
            options={options}
            placeholder={'Выберите значение...'}
            selection
            search
            value={initialValue}
            loading={isLoading}
            disabled={isLoading}
            onChange={onChange}
            onSearchChange={onSearchChange}
        />)
    }

    componentDidMount() {
        const {loadData, filter} = this.props;
        if (loadData) {
            loadData(filter);
        }
    }

    componentWillReceiveProps(nextProps) {
        const {filter, updateOnFilterChange} = this.props;
        if (updateOnFilterChange && this.isFilterNotEqual(filter, nextProps.filter)) {
            const {loadData} = nextProps;
            loadData(nextProps.filter)
        }
    }

    isFilterNotEqual(filter, nextFilter) {
        let isEqual = false;
        Object.keys(filter).forEach(key => {
            if (filter[key] !== nextFilter[key]) {
                isEqual = true;
            }
        });
        return isEqual;
    }
}


export default ObjectDropDown;
