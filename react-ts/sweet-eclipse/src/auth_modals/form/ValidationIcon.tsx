import React from 'react';


function ValidationIcon(props:
    {
        hasIcon: boolean | undefined,
        isInvalid: boolean | undefined | string
    }
) {
    return (
        <div>
            {(props.hasIcon !== undefined && props.hasIcon) ? (
                <span className="icon is-small is-right">
                    {( props.isInvalid !== "") ? (
                        props.isInvalid ? (
                            <i className="fas fa-exclamation-triangle"/>
                        ) : (
                            <i className="fas fa-check"/>
                        )
                    ) : null
                    }
                </span>
            ) : null
            }
        </div>
    );
}


export default ValidationIcon;