import React from 'react';

function BackToMainLinkButton(props: any) {
    return (
        <div>
            <a className="button is-primary is-light" href={"/"}>
                <span className="icon is-small">
                    <i className="fas fa-chevron-left"/>
                </span>
                <span>Return to Main Page</span>
            </a>
        </div>
    );
}

export default BackToMainLinkButton;