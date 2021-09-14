import React from 'react';

function CardImage(props: any) {

    const cardImageContainerStyle = {
        width: props.width ? props.width : 240
    };

    return (
        <div className="card-image" style={cardImageContainerStyle}>
            <figure className="image is-5by3">
                <img
                    src={props.imgSource}
                    alt={props.altText? props.altText : "An image"}
                />
            </figure>
        </div>
    );
}

export default CardImage;