import React from 'react';

function CardContent(props) {
    return (
        <div className="card-content">

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