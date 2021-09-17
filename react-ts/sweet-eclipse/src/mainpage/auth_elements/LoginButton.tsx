import React from 'react';
import {useAtom} from "jotai";
import {isLoggedInAtom} from "../../App";

function LoginButton(props: any) {

    const [isLoggedIn, setIsLoggedIn] = useAtom(isLoggedInAtom)
    let buttonStyle = {}
    if (isLoggedIn) {
        buttonStyle = {display: 'none'}
    }

    return (
        <a className="button is-light" style={buttonStyle}>
            Log in
        </a>
    );
}

export default LoginButton;