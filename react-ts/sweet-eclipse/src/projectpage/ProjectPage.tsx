import './projectpage.css';

import React, {useState, useEffect} from 'react';

import {useParams} from 'react-router-dom';
import Navbar from "../navbar/Navbar";
import BigImage from "./BigImage";
import ProgressBar from "./ProgressBar";
import FancyButton from "./buttons/FancyButton";
import {doGet} from "../util/Fetching";
import {Project} from "../ts-declarations/Project";
import SocialMediaContainer from "./SocialMediaContainer";
import ShareButton from "./buttons/ShareButton";
import TagContainer from "./tags/TagContainer";
import {useHistory} from "react-router-dom";

export const DEFAULT_PP_ELEMENT_WIDTH = "55vw";

function ProjectPage(props: any) {
    let {id} = useParams<{ id: string }>();

    const browserHistory = useHistory();

    const initialProject: Project = {
        currentFunds: 0,
        description: "",
        fundingGoal: 0,
        id: 0,
        images: [
            {
                id: 0,
                uri: ""
            }
        ],
        nrDonors: 0,
        status: "MISSING_INFO",
        tags: [
            {
                id: 0,
                name: ""
            }
        ],
        title: ""
    };

    const [project, setProject] = useState(
        initialProject
    );


    let percentValue = 0;

    useEffect(
        () => {
            doGet(
                'http://localhost:8080/api/project/' + id,
                (data: Project) => {
                    if (data.id && data.title) {
                        setProject(data)
                    } else {
                        setProject(initialProject)
                    }
                    console.log(data);
                }
            )
            return () => {
                //do cleanup here
            }
        },
        [id]
    )


    percentValue = project.fundingGoal ? Math.trunc(project.currentFunds / project.fundingGoal * 100) : 0;

    return (
        <div>
            <Navbar/>
            {project.status === "MISSING_INFO" ? "No such project!" : (
                <div className={"projectPageContainer"}>

                    <BigImage imgSource={project.images ? project.images[0].uri : ""}/>
                    <ProgressBar
                        currentFunds={project.currentFunds}
                        fundingGoal={project.fundingGoal}
                        nrDonors={project.nrDonors}
                        percentValue={percentValue}
                    />
                    <SocialMediaContainer>
                        <TagContainer tags={project.tags ? project.tags : []}/>
                        <ShareButton>Share</ShareButton>
                    </SocialMediaContainer>
                    <br/>

                    <div style={{width: DEFAULT_PP_ELEMENT_WIDTH}}>
                        <FancyButton
                            classAddons={" is-link is-large is-fullwidth"}
                            icon={"fas fa-piggy-bank"}
                        >
                            Donate!
                        </FancyButton>

                        <br/>
                        <br/>
                        <div className="title is-3">
                            {project.title}
                        </div>
                        <div className="content is-small">
                            {project.description}
                        </div>
                    </div>

                </div>
            )}
        </div>

    );
}

export default ProjectPage;