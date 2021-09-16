import React from 'react';
import {Tag} from "../../ts-declarations/Tag";
import TagEmblem from "./TagEmblem";

function TagContainer(props: { tags: Tag[] }) {
    return (
        <div>
            <div className={"is-flex is-justify-content-flex-start"}>
                {(props.tags.length > 0 ? (
                    <span className={"tag icon"}>
                        <i className="fas fa-tag"/>
                    </span>
                ) : null)}

                {props.tags.map(
                    (tag, index) => (
                        <TagEmblem key={index}>{tag.name}</TagEmblem>
                    )
                )}
            </div>
        </div>
    );
}

export default TagContainer;