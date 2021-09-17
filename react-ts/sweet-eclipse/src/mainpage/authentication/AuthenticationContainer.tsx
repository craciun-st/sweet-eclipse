import React from 'react';
import LoginButton from "./LoginButton";
import SignUpButton from "./SignUpButton";

function AuthenticationContainer(props: any) {
    return (
        <div className="buttons">
            <SignUpButton>Sign Up</SignUpButton>
            <LoginButton/>
        </div>
    );
}

export default AuthenticationContainer;