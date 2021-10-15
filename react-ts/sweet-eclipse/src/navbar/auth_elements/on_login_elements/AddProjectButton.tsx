import React from 'react';
import {useAtom} from "jotai";
import {isAddProjectModalActive, isLoggedInAtom} from "../../../GlobalAtoms";
import {useHistory} from "react-router-dom";
import AddProjectModal from "../../../project_related_elements/AddProjectModal";

function AddProjectButton(props: any) {
    const browserHistory = useHistory();
    const [isLoggedIn, setIsLoggedIn] = useAtom(isLoggedInAtom)
    const [shouldShowAddProjectModal, setShouldShowAddProjectModal] = useAtom(isAddProjectModalActive)
    let buttonStyle = {}
    if (!isLoggedIn) {
        buttonStyle = {display: 'none'}
    }

    function handleAddProjectClick() {
        setShouldShowAddProjectModal(true)
    }

    return (
        <div>

            <AddProjectModal />

            <button className="button is-info is-light addProjectButton" style={buttonStyle} onClick={handleAddProjectClick}>
                Add a new project
            </button>
        </div>
    );
}

export default AddProjectButton;