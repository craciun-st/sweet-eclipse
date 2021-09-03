import './projectpage.css';

import React, {useState, useEffect} from 'react';

import {useParams} from 'react-router-dom';
import Navbar from "../navbar/Navbar";
import BigImage from "./BigImage";
import ProgressBar from "./ProgressBar";
import FancyButton from "./FancyButton";
function ProjectPage(props) {
    let {id} = useParams();

    percentValue = project.fundingGoal ? Math.trunc(project.currentFunds/project.fundingGoal * 100) : 0;

    return (
        <div>
            <Navbar />

            <BigImage imgSource={project.images[0].uri}/>
            <ProgressBar
                currentFunds={project.currentFunds}
                fundingGoal={project.fundingGoal}
                nrDonors={project.nrDonors}
                percentValue={percentValue}
            />
            <div>
                <FancyButton>Donate!</FancyButton>
                <FancyButton>Share</FancyButton>
            </div>
            <br />
            <br />
            <div className="title is-3">
                {project.title}
            </div>
            <div className="content is-small">
                {project.description}
            </div>
        </div>
    );
}

export default ProjectPage;