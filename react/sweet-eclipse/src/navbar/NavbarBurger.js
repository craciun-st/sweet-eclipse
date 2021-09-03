import React from 'react';

function NavbarBurger(props) {
    return (
        <a role="button" className="navbar-burger" aria-label="menu" aria-expanded="false"
           data-target={props.idForExpandedMenu}>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    );
}

export default NavbarBurger;