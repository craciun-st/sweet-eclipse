import React from 'react';

function ErrorMessageDisplay(props: { hasError: boolean | string | undefined; errorMessage: any }) {
    return (
        <div>
            {(props.hasError && props.hasError !== "" && props.hasError !== undefined) ? (
                <p className="help is-danger">{props.errorMessage}</p>
            ) : null
            }
        </div>
    );
}

export default ErrorMessageDisplay;