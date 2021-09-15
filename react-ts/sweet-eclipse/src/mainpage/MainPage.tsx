import './mainpage.css';
import Footer from "./Footer";
import Navbar from "../navbar/Navbar";
import Card from "./card/Card";

import {useEffect, useState} from "react";

import {doGet} from "../util/Fetching";
import {Project} from "../ts-declarations/Project";

// const projects = [
//     {
//         "id": 1,
//         "status": "ACTIVE",
//         "title": "Save the stray dogs!",
//         "description": "Collaborate to find a shelter for these misunderstood beings",
//         "fundingGoal": 1000,
//         "images": [
//             {
//                 "id": 1,
//                 "uri": "/image_0001.png"
//             },
//             {
//                 "id": 2,
//                 "uri": "/image_0002.png"
//             }
//         ],
//         "tags": [
//             {
//                 "id": 1,
//                 "name": "pets"
//             }
//         ]
//     },
//     {
//         "id": 2,
//         "status": "ACTIVE",
//         "title": "Help fund the biggest river cleanup effort!",
//         "description": "The river X has too many junk floating in it! We need your help to finance a cleanup operation.",
//         "fundingGoal": 2000,
//         "images": [
//             {
//                 "id": 3,
//                 "uri": "/image_0003.png"
//             }
//         ],
//         "tags": [
//             {
//                 "id": 2,
//                 "name": "community"
//             },
//             {
//                 "id": 3,
//                 "name": "environment"
//             }
//         ]
//     }
// ]







function MainPage(props: any) {

    const initialProjects: Project[] = [];
    const [projects, setProjects] = useState(initialProjects);

    useEffect(
        () => {
            doGet(
                'http://localhost:8080/api/projects',
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
            <Navbar />


            <div className="is-flex is-flex-wrap-wrap is-justify-content-space-around">
                {projects.map((project, index) => (
                        <Card
                            key={index}
                            imgSource={project.images? project.images[0].uri : ""}
                            title={project.title}
                            titleUrlRedirect={'/project/'+project.id}
                            description={project.description}
                        />
                    )
                )
                }
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup1"
                    title="Mockup project"
                    titleUrlRedirect="#"
                    description="Description of project: Lorem ipsum dolor sit amet"
                />
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup2"
                    title="Mockup project 2"
                    titleUrlRedirect="#"
                    description="Description, etc: Lorem ipsum dolor..."
                />
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup3"
                    title="Mockup project 3"
                    titleUrlRedirect="#"
                    description="Description, etc: Lorem ipsum dolor..."
                />
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup4"
                    title="Mockup project 4"
                    titleUrlRedirect="#"
                    description="Description, etc: Lorem ipsum dolor..."
                />
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup5"
                    title="Mockup project 5"
                    titleUrlRedirect="#"
                    description="Description, etc: Lorem ipsum dolor..."
                />
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup6"
                    title="Mockup project 6"
                    titleUrlRedirect="#"
                    description="Description, etc: Lorem ipsum dolor..."
                />
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup7"
                    title="Mockup project 7"
                    titleUrlRedirect="#"
                    description="Description, etc: Lorem ipsum dolor..."
                />
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup8"
                    title="Mockup project 8"
                    titleUrlRedirect="#"
                    description="Description, etc: Lorem ipsum dolor..."
                />
                <Card
                    imgSource="https://via.placeholder.com/120x90/eee?text=Mockup9"
                    title="Mockup project 9"
                    titleUrlRedirect="#"
                    description="Description, etc: Lorem ipsum dolor..."
                />
            </div>



            <Footer />
        </div>
    );
}

export default MainPage;