import {connect} from 'react-redux'
import {changeAppState} from "../actions/application";
import NavigationBar from "../components/NavigationBar";

const mapStateToProps = (state) => {
    const {application} = state;
    const {activeItem} = application;
    const {workloads} = state[activeItem];
    return {
        activeItem,
        workloads
    }
};

const mapDispatchToProps = (dispatch) => ({
    handleItemClick: (e, {name}) => {
        dispatch(changeAppState({activeItem: name}))
    }
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(NavigationBar)