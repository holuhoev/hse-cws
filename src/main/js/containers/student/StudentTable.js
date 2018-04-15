import {connect} from 'react-redux'
import React from 'react'
import {fetchStudentDisciplineWorkload} from "../../actions/index";
import WorkloadTable from "../../components/WorkloadTable";

const mapStateToProps = state => {
    const {student} = state;
    const {workloadFilter, workloads} = student;
    return {
        loading: workloads.isFetching,
        data: workloads.items,
        filter: workloadFilter
    }
};

const mapDispatchToProps = dispatch => ({
    loadData: (filter) => {
        dispatch(fetchStudentDisciplineWorkload(filter))
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(WorkloadTable);