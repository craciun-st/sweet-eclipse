import React from 'react';

function CardContent(props) {

    const cardContentStyle = {
        width: props.width ? props.width : 300
    };


    return (
        <div className="card-content" style={cardContentStyle}>

            <div className="media-content">
                <p className="title is-5">
                    <a href={props.urlRedirect? props.urlRedirect : "#"}>
                        {props.title}
                    </a>
                </p>

            </div>
            <div className="content is-small">
                {props.description}
            </div>
        </div>
    );
}

export default CardContent;