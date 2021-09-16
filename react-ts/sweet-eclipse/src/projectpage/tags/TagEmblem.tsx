import React from 'react';

function TagEmblem(props: any) {
    return (
        <div>
            <div>

                <span className="tag is-rounded is-primary is-light">{props.children}</span>

            </div>
        </div>
    );
}

export default TagEmblem;