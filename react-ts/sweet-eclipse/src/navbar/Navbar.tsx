import React from 'react';
import BrandLogo from "./BrandLogo";
import NavbarBurger from "./NavbarBurger";
import DropdownMenu from "./DropdownMenu";
import NavbarItem from "./NavbarItem";
import AuthenticationContainer from "./auth_elements/AuthenticationContainer";

import './navbar.css'

function Navbar(props: any) {
    return (
        <div>
            <nav className="navbar" role="navigation" aria-label="main navigation">
                <div className="navbar-brand">
                    <BrandLogo urlRedirect="/" imgSource="/new_logo.png" />
                    <NavbarBurger idForExpandedMenu="navbarBasicExample" />
                </div>

                <div id="navbarBasicExample" className="navbar-menu">
                    <div className="navbar-start">
                        <NavbarItem name="Home" urlRedirect={"/"}/>
                        <NavbarItem name="Completed Projects" />
                        <DropdownMenu buttonName="More" />
                    </div>

                    <div className="navbar-end">
                        <div className="navbar-item">
                            <AuthenticationContainer />
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    );
}

export default Navbar;