
// import { useEffect, useState } from 'react';
import './App.css'
import axios from "axios";
import {
  BrowserRouter as Router,
  Routes,
  Route,

} from "react-router-dom";
import Navbar from './components/Navbar';
import Home from './components/Home';
import Home1 from './components/Home1';
import Profile from './components/profile/Profile';
import Login from './components/login/Login';
// import { useAppSelector } from './redux/hooks/index';

function App() {

  // const isLoggedIn = useAppSelector(state => state.loginr);
  // console.log(isLoggedIn);
  // console.log();

  // const [ticket, setTicket] = useState([]);

  // useEffect(() => {
  //   return () => {
  //     if (isLoggedIn) {
  //       //user is logged 
  //       const id = localStorage.getItem('auth');
  //       axios.get("http://localhost:8081/api/v1/auth/getuser/" + id).then(response => {
  //         console.log(response);
  //         setTicket(...ticket, response.data.userAssignTickets);
  //       }).catch(err => {
  //         console.log(err);
  //       })
  //     }

  //   }
  // }, [])

  return (
    <>
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/profile' element={<Profile />} />
          <Route path='/login' element={<Login />} />

        </Routes>
      </Router>

    </>
  )
}

export default App
