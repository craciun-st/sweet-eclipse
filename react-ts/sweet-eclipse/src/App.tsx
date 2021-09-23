import './App.css';
import MainPage from './mainpage/MainPage';
import 'bulma/css/bulma.css';

import {BrowserRouter, Route, Switch} from "react-router-dom";
import ProjectPage from "./projectpage/ProjectPage";
import DonatePage from "./donation/DonatePage";



function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Switch>
                <Route exact path="/"><MainPage /></Route>
                {/*<Route path="/my-account"><ProfilePage /></Route>*/}
                <Route path="/project/:id" children={<ProjectPage />} />
                <Route path="/donate"><DonatePage /></Route>
                {/*<Route path="/signup"><SignUpPage /></Route>*/}
            </Switch>
        </BrowserRouter>
    </div>
  );
}

export default App;
