import React, {Component} from 'react'
import {Dropdown} from 'semantic-ui-react'


class ObjectDropDown extends Component {
    render() {
        const {initialValue, options, isLoading, onChange, onSearchChange, placeHolder} = this.props;
        return (<Dropdown
            options={options}
            placeholder={placeHolder}
            noResultsMessage={'Нет данных...'}
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
        if (filter && updateOnFilterChange && this.notEqual(filter, nextProps.filter)) {
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
}


export default ObjectDropDown;
