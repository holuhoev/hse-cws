import {connect} from 'react-redux'
import React from 'react'
import {fetchStudentDisciplineWorkload} from "../../actions/index";
import WorkloadTable from "../../components/WorkloadTable";

const mapStateToProps = state => {
    const {studentDisciplineWorkload} = state;
    const {workloadFilter, workloads} = studentDisciplineWorkload;
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