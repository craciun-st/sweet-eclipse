import React from 'react';

function SignUpButton(props: any) {
    return (
        <a className="button is-primary" href={"/signup"}>
            <strong>Sign up</strong>
        </a>
    );
}

export default SignUpButton;