import './mainpage.css';
import Footer from "./Footer";
import Navbar from "./navbar/Navbar";
import Card from "./card/Card";

function MainPage(props) {
    return (
        <div>
            <Navbar />



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



            <Footer />
        </div>
    );
}

export default MainPage;