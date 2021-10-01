import './mainpage.css';
import Footer from "./Footer";
import Navbar from "../navbar/Navbar";
import Card from "./card/Card";

import {useEffect, useState} from "react";

import {doGet} from "../util/Fetching";
import {Project} from "../ts-declarations/Project";
import {getFirstSentenceUpTo} from "../util/StringUtils";
import {SERVER_URL} from "../index";


function MainPage(props: any) {

    const initialProjects: Project[] = [];
    const [projects, setProjects] = useState(initialProjects);

    useEffect(
        () => {
            doGet(
                SERVER_URL + '/api/projects',
                (data: Project[]) => setProjects(data)
            )
            return () => {
                //do cleanup here
            }
        },
        []
    )


    return (
        <div>
            <Navbar/>


            <div className="mainPageContainer">
                {projects.map((project, index) => (
                        <Card
                            key={index}
                            imgSource={project.images ? project.images[0].uri : ""}
                            title={project.title}
                            titleUrlRedirect={'/project/' + project.id}
                            description={
                                project.description !== undefined ? getFirstSentenceUpTo(project.description, 128) : ""
                            }
                        />
                    )
                )
                }
            </div>


            <Footer/>
        </div>
    );
}

export default MainPage;