import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {Elements} from "@stripe/react-stripe-js";
import {loadStripe} from "@stripe/stripe-js/pure";


export const SERVER_URL = "http://localhost:8080"

export const apiKey = process.env.REACT_APP_STRIPE_API_PUBLISHABLE_KEY;

if (apiKey === undefined) {
    ReactDOM.render(
        <React.StrictMode>
            <div>Missing environment variables! Could not start...</div>
        </React.StrictMode>,
        document.getElementById('root')
    );
} else {

    const stripePromise = loadStripe(apiKey);

    ReactDOM.render(
        <React.StrictMode>
            <Elements stripe={stripePromise}>
                <App/>
            </Elements>
        </React.StrictMode>,
        document.getElementById('root')
    );
}
