import React from 'react';
import {useAtom} from "jotai";
import {
    amountToDonate,
    idForDonationIntent,
    imageUriForDonationIntent,
    localClientSecret,
    titleForDonationIntent
} from "../GlobalAtoms";
import DonateForm from "./DonateForm";
import BackToMainButton from "../general_buttons/BackToMainButton";
import {useHistory} from "react-router-dom";
import './donatepage.css'
import CardSection from "./CardSection";
import {CardElement, useElements, useStripe} from "@stripe/react-stripe-js";
import BackToMainLinkButton from "../general_buttons/BackToMainLinkButton";

function DonatePage(props: any) {

    const [titleForProject, setTitleForProject] = useAtom(titleForDonationIntent);
    const [imageUriForProject, setImageUriForProject] = useAtom(imageUriForDonationIntent);
    const [idForProject, setIdForProject] = useAtom(idForDonationIntent);
    const [clientSecret, setClientSecret] = useAtom(localClientSecret);
    const [amountForProject] = useAtom(amountToDonate)



    const fullHeightStyle = {
        height: "100vh"
    }

    const stripe = useStripe();
    const elements = useElements();

    const handleSubmit = async (event: any) => {
        // We don't want to let default form submission happen here,
        // which would refresh the page.
        event.preventDefault();

        if (!stripe || !elements) {
            // Stripe.js has not yet loaded.
            // Make sure to disable form submission until Stripe.js has loaded.
            return;
        }
        const cardTokenElement = elements.getElement(CardElement);
        const result = await stripe.confirmCardPayment(clientSecret, {
            payment_method: {
                card: cardTokenElement !== null ? cardTokenElement : {token: "EMPTY"},
                billing_details: {
                    email: 'test@test.com'
                },
            }
        });

        if (result.error) {
            // Show error to your customer (e.g., insufficient funds)
            alert("Sadly, payment has failed");
            setClientSecret("")
            console.log(result.error.message);
        } else {
            // The payment has been processed!
            if (result.paymentIntent.status === 'succeeded') {
                alert('Payment has succeeded!');
                setClientSecret("")
            }
        }
    };


    if (!titleForProject || !idForProject || (clientSecret === "")) {
        return (
            <div>
                Sorry, can't donate right now!
                <BackToMainLinkButton/>
            </div>
        );
    } else {

        return (
            <div className={"donateContainer"}>
                <div className={"darkSideBar"} style={fullHeightStyle}>
                    <img src={"/new_logo.png"} width={"20vw"} alt={"App logo"}/>
                </div>
                <div className={"donateFormMainBar"} style={fullHeightStyle}>
                    <div className={"is-flex is-flex-direction-row  is-justify-content-space-around"}>
                        <div className={"is-text"}>
                            You are donating {amountForProject} <i>EUR</i> to <b>{titleForProject}</b>:
                        </div>
                        <img src={imageUriForProject} alt={"Image for project " + titleForProject} width={"120px"}/>
                    </div>
                    <form onSubmit={handleSubmit}>
                        <CardSection/>
                        <br/>
                        <button className={"button is-large is-danger"} disabled={!stripe}>Confirm order</button>
                    </form>

                </div>
            </div>
        );
    }
}

export default DonatePage;