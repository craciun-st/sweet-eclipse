import React from 'react';
import {useAtom} from "jotai";
import {isAddProjectModalActive, isLoggedInAtom, isLoginModalActive} from "../GlobalAtoms";
import LoginForm from "../auth_modals/login/LoginForm";
import AddProjectForm from "./AddProjectForm";

function AddProjectModal(props: any) {
    const [isActive, setIsActive] = useAtom(isAddProjectModalActive)

    function closeModal() {
        setIsActive(false)
    }

    function handleSubmitSuccess(data: {}) {
        // do some stuff
        closeModal();
    }

    return (
        <div className={"modal" + (isActive ? " is-active" : "")}>
            <div className="modal-background"/>
            <div className="modal-card">
                <header className="modal-card-head">
                    <p className="modal-card-title">Add a new project</p>
                    <button className="delete" aria-label="close" onClick={closeModal}/>
                </header>
                <section className="modal-card-body">
                    <AddProjectForm onSuccessfulCreate={(data => handleSubmitSuccess(data))}/>
                </section>
                {/*<footer className="modal-card-foot">*/}
                {/*    <BackToMainButton classNameAddon={" is-outlined"} onClick={closeModal}/>*/}
                {/*</footer>*/}
            </div>
        </div>
    );
}

export default AddProjectModal;