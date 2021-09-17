import React from 'react';
import BackToMainLinkButton from "../general_buttons/BackToMainLinkButton";
import SignUpForm from "./SignUpForm";

function SignUpPage(props: any) {
    return (
        <div>
            <h1>Signup</h1>
            <SignUpForm/>
            <BackToMainLinkButton/>
        </div>
    );
}

export default SignUpPage;