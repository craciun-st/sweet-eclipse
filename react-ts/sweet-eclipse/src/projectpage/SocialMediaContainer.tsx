import React from 'react';
import {DEFAULT_PP_ELEMENT_WIDTH} from "./ProjectPage";


function SocialMediaContainer(props: any) {
    const containerStyle = {width: props.width ? props.width : DEFAULT_PP_ELEMENT_WIDTH}
    return (
        <div className={"socialMediaContainer"} style={containerStyle}>
            {props.children}
        </div>
    );
}

export default SocialMediaContainer;