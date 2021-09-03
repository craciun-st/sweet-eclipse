import React from 'react';
import BrandLogo from "./BrandLogo";
import NavbarBurger from "./NavbarBurger";
import DropdownMenu from "./DropdownMenu";
import NavbarItem from "./NavbarItem";
import AuthenticationContainer from "../mainpage/authentication/AuthenticationContainer";

function Navbar(props) {
    return (
        <div>
            <nav className="navbar" role="navigation" aria-label="main navigation">
                <div className="navbar-brand">
                    <BrandLogo urlRedirect="/" imgSource="/logo.png" />
                    <NavbarBurger idForExpandedMenu="navbarBasicExample" />
                </div>

                <div id="navbarBasicExample" className="navbar-menu">
                    <div className="navbar-start">
                        <NavbarItem name="Home" />
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