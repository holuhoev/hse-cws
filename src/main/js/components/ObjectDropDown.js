import React, {Component} from 'react'
import {Dropdown, Button, Grid,Segment} from 'semantic-ui-react'


class ObjectDropDown extends Component {
    render() {
        const {initialValue, label, options, isLoading, onChange, onSearchChange, onRemoveButtonClick, placeHolder} = this.props;
        return (
            <Grid>
                <Grid.Column >
                    {label ? label + ': ' : ''}
                    <Dropdown
                        button
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
                    />
                    <Button icon='remove' onClick={onRemoveButtonClick}/>
                </Grid.Column>
            </Grid>

        )
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
