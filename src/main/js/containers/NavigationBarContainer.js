import {connect} from 'react-redux'
import {setActiveItem} from "../actions/application";
import NavigationBar from "../components/NavigationBar";

const mapStateToProps = (state) => {
    const {application} = state;
    const {activeItem} = application;
    return {
        activeItem
    }
};

const mapDispatchToProps = (dispatch) => ({
    onButtonClick: () => {
        // TODO???
    },
    handleItemClick: (e, {name}) => {
        dispatch(setActiveItem(name))
    }
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(NavigationBar)