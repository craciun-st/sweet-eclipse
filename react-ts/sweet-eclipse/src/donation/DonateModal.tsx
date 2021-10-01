import React from 'react';
import {useAtom} from "jotai";
import {
    idForDonationIntent,
    imageUriForDonationIntent,
    isDonateModalActive,
    titleForDonationIntent
} from "../GlobalAtoms";
import DonateForm from "./DonateForm";
import {useHistory} from "react-router-dom";
import BackToMainButton from "../general_buttons/BackToMainButton";

function DonateModal(props: any) {
    const [isActive, setIsActive] = useAtom(isDonateModalActive)
    const [titleForProject, setTitleForProject] = useAtom(titleForDonationIntent);
    const [imageUriForProject, setImageUriForProject] = useAtom(imageUriForDonationIntent);
    const [idForProject, setIdForProject] = useAtom(idForDonationIntent);
    const browserHistory = useHistory();

    function closeModal() {
        setIsActive(false)
    }

    function handleSubmitSuccess() {

        closeModal();

    }

    return (
        <div className={"modal" + (isActive ? " is-active" : "")}>
            <div className="modal-background"/>
            <div className="modal-card">
                <header className="modal-card-head">
                    <p className="modal-card-title">Donate</p>
                    <button className="delete" aria-label="close" onClick={closeModal}/>
                </header>
                <section className="modal-card-body">
                    <DonateForm onSuccessfulCreate={handleSubmitSuccess}/>
                </section>
                <footer className="modal-card-foot">
                    <BackToMainButton onClick={(event) => {
                        setImageUriForProject("");
                        setTitleForProject("");
                        setIdForProject(-1);
                        closeModal();
                    }} classNameAddon={" is-outlined block"} />
                </footer>
            </div>
        </div>
    );
}

export default DonateModal;