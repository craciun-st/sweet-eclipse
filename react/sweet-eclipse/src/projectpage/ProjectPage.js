import './projectpage.css';

import React, {useState, useEffect} from 'react';

import {useParams} from 'react-router-dom';
import Navbar from "../navbar/Navbar";
import BigImage from "./BigImage";
import ProgressBar from "./ProgressBar";
import FancyButton from "./FancyButton";
import {doGet} from "../util/Fetching";

function ProjectPage(props) {
    let {id} = useParams();

    const [project, setProject] = useState(
        {
            images: [
                {
                    uri:""
                }
            ]
        }
    );


    let percentValue = 0;

    useEffect(
        () => {
            doGet(
                'http://localhost:8080/api/project/'+id.toString(),
                (data) => {setProject(data); console.log(data);
                    }
            )
            return () => {
                //do cleanup here
            }
        },
        [id]
    )


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