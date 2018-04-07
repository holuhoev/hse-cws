import React from 'react'
import PropTypes from 'prop-types'

const DisciplineWorkloads = ({items}) => (
    <ul>
        {items.map(item => {
            <li key={item.id}>{item.name}</li>
        })}
    </ul>
);

DisciplineWorkloads.PropTypes = {
    items: PropTypes.array.isRequired
};

export default DisciplineWorkloads;
