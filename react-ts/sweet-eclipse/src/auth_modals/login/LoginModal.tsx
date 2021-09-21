import React from 'react';
import {useAtom} from "jotai";
import LoginForm from "./LoginForm";
import {isLoggedInAtom, isLoginModalActive} from "../../GlobalAtoms";

function LoginModal(props: any) {
    const [isActive, setIsActive] = useAtom(isLoginModalActive)
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
                    <p className="modal-card-title">Login</p>
                    <button className="delete" aria-label="close" onClick={closeModal}/>
                </header>
                <section className="modal-card-body">
                    <LoginForm onSuccessfulCreate={(data => handleSubmitSuccess(data))}/>
                </section>
                {/*<footer className="modal-card-foot">*/}
                {/*    <BackToMainButton classNameAddon={" is-outlined"} onClick={closeModal}/>*/}
                {/*</footer>*/}
            </div>
        </div>
    );
}

export default LoginModal;