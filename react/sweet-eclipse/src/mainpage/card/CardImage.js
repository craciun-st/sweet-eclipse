import React from 'react';

function CardImage(props) {
    return (
        <div className="card-image">
            <figure className="image is-4by3">
                <img
                    src={props.imgSource}
                    width={props.width? props.width : "120"}
                    alt={props.altText? props.altText : "An image"}
                />
            </figure>
        </div>
    );
}

export default CardImage;