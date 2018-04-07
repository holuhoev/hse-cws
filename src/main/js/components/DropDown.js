import React from 'react'
import PropTypes from 'prop-types'
import ReactSuperSelect from 'react-super-select'

const DropDown = ({initialValue, data, onClick, placeHolder}) => (
    <ReactSuperSelect clearSearchOnSelection={true}
                      deselectOnSelectedOptionClick={false}
                      dataSource={data.items}
                      initialValue={initialValue}
                      onChange={onClick}
                      searchable={true}
                      disabled={data.isFetching}
    />
);

DropDown.PropTypes = {
    data: PropTypes.shape({
        isFetching: PropTypes.bool.isRequired,
        items: PropTypes.array.isRequired
    }),
    initialValue: PropTypes.object,
    onClick: PropTypes.func.isRequired,
    placeHolder: PropTypes.string
};

export default DropDown;
