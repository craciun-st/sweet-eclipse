import './mainpage.css';
import Footer from "./Footer";
import Navbar from "./navbar/Navbar";
import Card from "./card/Card";

function MainPage(props) {
    return (
        <div>
            <Navbar />


            <div className="is-flex is-flex-wrap-wrap">
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