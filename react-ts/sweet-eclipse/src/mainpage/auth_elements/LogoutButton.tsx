import React from 'react';
import {useAtom} from "jotai";
import {isLoggedInAtom} from "../../App";

function LogoutButton(props: any) {
    const [isLoggedIn, setIsLoggedIn] = useAtom(isLoggedInAtom)
    let buttonStyle = {}
    if (!isLoggedIn) {
        buttonStyle = {display: 'none'}
    }

    function handleLogoutClick() {
        setIsLoggedIn(false);
        localStorage.setItem('sweetEclipse', '')
    }

    return (
        <div>



            <button className="button is-warning is-light" style={buttonStyle} onClick={handleLogoutClick}>
                Log out
            </button>
        </div>
    );
}

export default LogoutButton;