import React from 'react';
import FancyButton from "./FancyButton";
import {useHistory} from "react-router-dom";
import {useAtom} from "jotai";
import {idForDonationIntent, imageUriForDonationIntent, titleForDonationIntent} from "../../GlobalAtoms";

function DonateButton(props: {
        forProjectTitle: string;
        forProjectImage: string;
        forProjectId: number;
    }
) {

    const [titleForProject, setTitleForProject] = useAtom(titleForDonationIntent);
    const [imageUriForProject, setImageUriForProject] = useAtom(imageUriForDonationIntent);
    const [idForProject, setIdForProject] = useAtom(idForDonationIntent);
    const browserHistory = useHistory();

    function handleClick(event: any) {
        setTitleForProject(props.forProjectTitle);
        setImageUriForProject(props.forProjectImage);
        setIdForProject(props.forProjectId);
        browserHistory.push("/donate")
    }

    return (
            <div>
            <FancyButton
                classAddons={" is-link is-large is-fullwidth"}
                icon={"fas fa-piggy-bank"}
                onClick={handleClick}
            >
                Donate!
            </FancyButton>

            </div>

    );
}

export default DonateButton;