import React from 'react';
import {useAtom} from "jotai";
import SignUpForm from "./SignUpForm";
import BackToMainButton from "../../general_buttons/BackToMainButton";
import {isLoggedInAtom, isSignUpModalActive} from "../../GlobalAtoms";

function SignUpModal(props: any) {
    const [isActive, setIsActive] = useAtom(isSignUpModalActive)
    const [isLoggedIn, setIsLoggedIn] = useAtom(isLoggedInAtom)

    function closeModal() {
        setIsActive(false)
    }

    function handleSubmitSuccess(data: {}) {
        localStorage.setItem('sweetEclipse', JSON.stringify(data));
        closeModal();
        setIsLoggedIn(true);
    }

    return (
        <div className={"modal" + (isActive ? " is-active" : "")}>
            <div className="modal-background"/>
            <div className="modal-card">
                <header className="modal-card-head">
                    <p className="modal-card-title">Sign Up</p>
                    <button className="delete" aria-label="close" onClick={closeModal}/>
                </header>
                <section className="modal-card-body">
                    <SignUpForm onSuccessfulCreate={(data => handleSubmitSuccess(data))}/>
                </section>
                <footer className="modal-card-foot">
                    {/*<button className="button is-success">Save changes</button>*/}
                    {/*<button className="button" onClick={closeModal}>Cancel</button>*/}
                    <BackToMainButton classNameAddon={" is-outlined"} onClick={closeModal}/>
                </footer>
            </div>
        </div>
    );
}

export default SignUpModal;