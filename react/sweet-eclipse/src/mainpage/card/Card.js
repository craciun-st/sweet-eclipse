import React from 'react';
import CardImage from "./CardImage";
import CardContent from "./CardContent";

function Card(props) {
    return (
        <div className="card">
            <CardImage imgSource={props.imgSource} altText={props.imgAltText} />
            <CardContent title={props.title} description={props.description} urlRedirect={props.titleUrlRedirect} />
        </div>
    );
}

export default Card;