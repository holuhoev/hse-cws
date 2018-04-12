import {connect} from 'react-redux'
import WorkloadTable from "../../components/WorkloadTable";
import {fetchLecturerDisciplineWorkload} from "../../actions/lecturer/workloads";

const mapStateToProps = state => {
    const {lecturerDisciplineWorkload} = state;
    const {workloadFilter, workloads} = lecturerDisciplineWorkload;
    return {
        loading: workloads.isFetching,
        data: workloads.items,
        filter: workloadFilter
    }
};

const mapDispatchToProps = dispatch => ({
    loadData: (filter) => {
        dispatch(fetchLecturerDisciplineWorkload(filter))
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(WorkloadTable);