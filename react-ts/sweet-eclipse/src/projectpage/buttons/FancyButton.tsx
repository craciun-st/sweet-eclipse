import React from 'react';

export function FancyButton(props: any) {
    const classNameAddons = props.classAddons ? props.classAddons : "";
    const iconClassname = props.icon ? props.icon : "fas fa-pen-fancy";
    return (
        <div>
            <button className={"button" + classNameAddons} onClick={props.onClick}>
                <span className="icon">
                    <i className={iconClassname}/>
                </span>
                <span>
                    {props.children}
                </span>

            </button>
        </div>
    );
}

export default FancyButton;