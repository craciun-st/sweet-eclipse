import React from 'react';

function BigImage(props) {
    const bigImageContainerStyle = {
        width: props.width ? props.width : "55vw"
    };
    return (
        <div style={bigImageContainerStyle}>
            <figure className="image is-5by3" >
                <img
                    src={props.imgSource}
                    alt={props.altText? props.altText : "An image"}
                />
            </figure>
        </div>
    );
}

export default BigImage;