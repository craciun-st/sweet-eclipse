import './projectpage.css';

import React, {useState, useEffect} from 'react';

import {useParams} from 'react-router-dom';
import Navbar from "../navbar/Navbar";
function ProjectPage(props) {
    let {id} = useParams();


    return (
        <div>
            <Navbar />

        </div>
    );
}

export default ProjectPage;