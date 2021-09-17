import React from 'react';
import SignUpModal, {isSignUpModalActive} from "../../auth_modals/SignUpModal";
import {useAtom} from "jotai";



function SignUpButton(props: any) {

    const [shouldDisplayModal, setShouldDisplayModal] = useAtom(isSignUpModalActive)

    function launchSignUpModal() {
        setShouldDisplayModal(true);
    }
    return (
        <div>
            <button className="button is-primary is-light" onClick={launchSignUpModal}>
                <strong>{props.children}</strong>
            </button>
            <SignUpModal isActive={shouldDisplayModal}/>
        </div>
    );


}

export default SignUpButton;