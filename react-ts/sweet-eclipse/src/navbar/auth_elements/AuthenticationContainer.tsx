import React from 'react';
import LoginButton from "./LoginButton";
import SignUpButton from "./SignUpButton";
import LogoutButton from "./LogoutButton";
import WelcomeMessage from "./WelcomeMessage";
import AddProjectButton from "./on_login_elements/AddProjectButton";

function AuthenticationContainer(props: any) {
    return (


            <div className="buttons authenticationContainer">
                <AddProjectButton />
                <WelcomeMessage/>
                <SignUpButton>Sign Up</SignUpButton>
                <LoginButton/>
                <LogoutButton/>
            </div>

    );
}

export default AuthenticationContainer;