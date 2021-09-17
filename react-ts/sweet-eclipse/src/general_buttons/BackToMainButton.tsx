import React from 'react';

function BackToMainButton(props: {
    onClick: React.MouseEventHandler<HTMLButtonElement> | undefined;
    classNameAddon: string
}) {
    return (
        <button className={"button is-primary is-light" + props.classNameAddon} onClick={props.onClick}>
                <span className="icon is-small">
                    <i className="fas fa-chevron-left"/>
                </span>
            <span>Return to Main Page</span>
        </button>
    );
}

export default BackToMainButton;