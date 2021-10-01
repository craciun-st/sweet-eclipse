import React from 'react';
import {useAtom} from "jotai";
import {idForDonationIntent, imageUriForDonationIntent, titleForDonationIntent} from "../GlobalAtoms";
import DonateForm from "./DonateForm";
import BackToMainButton from "../general_buttons/BackToMainButton";
import {useHistory} from "react-router-dom";
import './donatepage.css'

function DonatePage(props: any) {

    const [titleForProject, setTitleForProject] = useAtom(titleForDonationIntent);
    const [imageUriForProject, setImageUriForProject] = useAtom(imageUriForDonationIntent);
    const [idForProject, setIdForProject] = useAtom(idForDonationIntent);
    const browserHistory = useHistory();

    const fullHeightStyle = {
        height: "100vh"
    }

    return (
        <div className={"donateContainer"}>
            <div className={"darkSideBar"} style={fullHeightStyle}>
                <img src={"/new_logo.png"} width={"20vw"} />
            </div>
            <div className={"donateFormMainBar"} style={fullHeightStyle}>
                <DonateForm className={"block"}/>
                <br />
                <BackToMainButton onClick={(event) => {
                    setImageUriForProject("");
                    setTitleForProject("");
                    setIdForProject(-1);
                    browserHistory.push("/")
                }} classNameAddon={" block"} />
            </div>
        </div>
    );
}

export default DonatePage;