import React from 'react';
import {DEFAULT_PP_ELEMENT_WIDTH} from "./ProjectPage";

function ProgressBar(props: any) {
    const ProgressBarContainerStyle = {
        width: props.width ? props.width : DEFAULT_PP_ELEMENT_WIDTH
    };


    return (
        <div style={ProgressBarContainerStyle}>
            <div className="is-flex is-justify-content-space-between">
                <div className="is-size-4">
                    <strong>{props.currentFunds}</strong> <i className="fas fa-piggy-bank"></i>
                </div>
                <div className="is-size-5">
                    <strong>{props.nrDonors}</strong> donors
                </div>
            </div>
            <progress className="progress is-info outlinedProgressBar" value={props.percentValue} max="100">{props.percentValue}%</progress>
            <div className="is-size-6">
                {props.percentValue}% of <i className="fas fa-piggy-bank"></i> {props.fundingGoal} Goal
            </div>
        </div>
    );
}

export default ProgressBar;