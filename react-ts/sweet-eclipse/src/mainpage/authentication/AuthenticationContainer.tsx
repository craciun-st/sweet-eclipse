import React from 'react';
import SignUpButton from "./SignUpButton";
import LoginButton from "./LoginButton";

function AuthenticationContainer(props: any) {
    return (
        <div className="buttons">
            <SignUpButton />
            <LoginButton />
        </div>
    );
}

export default AuthenticationContainer;