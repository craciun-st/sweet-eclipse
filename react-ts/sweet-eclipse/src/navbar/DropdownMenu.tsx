import React from 'react';
import NavbarItem from "./NavbarItem";

function DropdownMenu(props: any) {
    return (
        <div className="navbar-item has-dropdown is-hoverable">
            <a className="navbar-link">
                {props.buttonName}
            </a>

            <div className="navbar-dropdown">
                <NavbarItem name="About" />
                <NavbarItem name="Contact" />
                <hr className="navbar-divider" />
                <NavbarItem name="Report an issue" />
            </div>
        </div>
    );
}

export default DropdownMenu;