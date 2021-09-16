import React from 'react';
import FancyButton from "./FancyButton";

function ShareButton(props: any) {
    return (
        <FancyButton classAddons={" is-info is-small"} icon={"fab fa-twitter"}>{props.children}</FancyButton>
    );
}

export default ShareButton;