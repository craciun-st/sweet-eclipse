import React from 'react';
import {useAtom} from "jotai";
import LoginModal from "../../auth_modals/login/LoginModal";
import SignUpModal from "../../auth_modals/signup/SignUpModal";
import {isLoggedInAtom, isLoginModalActive} from "../../GlobalAtoms";

function LoginButton(props: any) {

    const [isLoggedIn, setIsLoggedIn] = useAtom(isLoggedInAtom)
    const [shouldDisplayModal, setShouldDisplayModal] = useAtom(isLoginModalActive)
    let buttonStyle = {}
    if (isLoggedIn) {
        buttonStyle = {display: 'none'}
    }

    function launchLoginModal() {
        setShouldDisplayModal(true);
    }

    return (
        <div>
            <button className="button is-light" style={buttonStyle} onClick={launchLoginModal}>
                Log in
            </button>
            <LoginModal isActive={shouldDisplayModal} />
        </div>
    );
}

export default LoginButton;