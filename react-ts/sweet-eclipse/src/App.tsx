import './App.css';
import MainPage from './mainpage/MainPage';
import 'bulma/css/bulma.css';

import {BrowserRouter, Route, Switch} from "react-router-dom";
import ProjectPage from "./projectpage/ProjectPage";
import {atom} from "jotai";

export const isLoggedInAtom = atom(false)


function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Switch>
                <Route exact path="/"><MainPage /></Route>
                {/*<Route path="/my-account"><ProfilePage /></Route>*/}
                <Route path="/project/:id" children={<ProjectPage />} />
                {/*<Route path="/signup"><SignUpPage /></Route>*/}
            </Switch>
        </BrowserRouter>
    </div>
  );
}

export default App;
