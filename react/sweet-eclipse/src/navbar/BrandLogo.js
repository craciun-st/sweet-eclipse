import React from 'react';

function BrandLogo(props) {
    return (
        <a className="navbar-item" href={props.urlRedirect}>
            <img src={props.imgSource} height="32" alt="Brand logo"/>
        </a>
    );
}

export default BrandLogo;