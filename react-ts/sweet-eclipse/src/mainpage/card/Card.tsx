import React from 'react';
import CardImage from "./CardImage";
import CardContent from "./CardContent";

function Card(props: any) {
    return (
        <div className="card cardSizeAdjust">
            <CardImage imgSource={props.imgSource} altText={props.imgAltText} />
            <CardContent title={props.title} description={props.description} urlRedirect={props.titleUrlRedirect} />
        </div>
    );
}

export default Card;