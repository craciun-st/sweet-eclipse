import React from 'react';
import {DEFAULT_PP_ELEMENT_WIDTH} from "./ProjectPage";

function BigImage(props: any) {
    const bigImageContainerStyle = {
        width: props.width ? props.width : DEFAULT_PP_ELEMENT_WIDTH
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