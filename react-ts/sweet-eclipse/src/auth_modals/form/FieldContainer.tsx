import React, {Component} from 'react';

function FieldContainer(props: any) {
    return (
        <div className="field">
            <label className="label">{props.labelText}</label>

            {props.children}

        </div>
);

}

export default FieldContainer;