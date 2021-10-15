import React from 'react';
import {useAtom} from "jotai";
import {ClientPersistedData} from "../../ts-declarations/ClientPersistedData";
import {isLoggedInAtom} from "../../GlobalAtoms";

function WelcomeMessage(props: any) {
    const [isLoggedIn, setIsLoggedIn] = useAtom(isLoggedInAtom)
    let messageStyle = {}
    let username = "";
    if (!isLoggedIn) {
        messageStyle = {display: 'none'}
    } else {
        let localData : ClientPersistedData = JSON.parse(localStorage.getItem("sweetEclipse") as string);
         username = localData.account.toString();
    }
    return (
        <div className={"button is-white is-disabled welcomeMessage"} style={messageStyle}>
            Welcome, {username}!
        </div>
    );
}

export default WelcomeMessage;