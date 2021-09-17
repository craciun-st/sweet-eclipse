import React from 'react';
import LoginButton from "./LoginButton";
import SignUpButton from "./SignUpButton";
import LogoutButton from "./LogoutButton";
import WelcomeMessage from "./WelcomeMessage";

function AuthenticationContainer(props: any) {
    return (


            <div className="buttons">
                <WelcomeMessage/>
                <SignUpButton>Sign Up</SignUpButton>
                <LoginButton/>
                <LogoutButton/>
            </div>

    );
}

export default AuthenticationContainer;