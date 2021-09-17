import React from 'react';

function SignUpLinkButton(props: {
    children: any;
    urlRedirect: string}) {
    return (
        <a className="button is-primary" href={props.urlRedirect ? props.urlRedirect : undefined}>
            <strong>{props.children}</strong>
        </a>
    );
}

export default SignUpLinkButton;