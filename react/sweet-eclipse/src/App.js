import './App.css';
import MainPage from './mainpage/MainPage';
import 'bulma/css/bulma.css';

import {
    BrowserRouter,
    Route,
    Switch
} from "react-router-dom";




function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Switch>
                <Route exact path="/"><MainPage /></Route>
                {/*<Route path="/my-account"><ProfilePage /></Route>*/}
            </Switch>
        </BrowserRouter>
    </div>
  );
}

export default App;
