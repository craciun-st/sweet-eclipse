import React from 'react';
import SignUpModal from "../../auth_modals/signup/SignUpModal";
import {useAtom} from "jotai";
import {isLoggedInAtom, isSignUpModalActive} from "../../GlobalAtoms";



function SignUpButton(props: any) {


    const [shouldDisplayModal, setShouldDisplayModal] = useAtom(isSignUpModalActive)
    const [isLoggedIn, setIsLoggedIn] = useAtom(isLoggedInAtom)
    let buttonStyle = {}
    if (isLoggedIn) {
        buttonStyle = {display: 'none'}
    }
    function launchSignUpModal() {
        setShouldDisplayModal(true);
    }
    return (
        <div>
            <button className="button is-primary" onClick={launchSignUpModal} style={buttonStyle}>
                <strong>{props.children}</strong>
            </button>
            <SignUpModal isActive={shouldDisplayModal}/>
        </div>
    );


}

export default SignUpButton;