import React from 'react';

function FancyButton(props: any) {
    return (
        <div>
            {props.children}
        </div>
    );
}

export default FancyButton;