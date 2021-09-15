import React from 'react';

function NavbarItem(props: any) {
    return (
        <a className="navbar-item" href={props.urlRedirect ? props.urlRedirect : null}>
            {props.name}
        </a>
    );
}

export default NavbarItem;