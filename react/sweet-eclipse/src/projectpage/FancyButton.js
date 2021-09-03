import React from 'react';

function FancyButton(props) {
    return (
        <div>
            {props.children}
        </div>
    );
}

export default FancyButton;