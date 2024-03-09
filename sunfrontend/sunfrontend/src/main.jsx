import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import './index.css';
import { Provider } from 'react-redux';
import { store } from './redux/store';
// import {
//   createBrowserRouter,
//   RouterProvider,
// } from "react-router-dom";
// import Profile from './components/profile/Profile.jsx';
// import Navbar from './components/Navbar.jsx';
// import Login from './components/login/Login.jsx';

// const router = createBrowserRouter([
//   {
//     path: "/",
//     element: <App />,
//   },
//   {
//     path: "/profile",
//     element: <Profile />,
//   },
//   {
//     path: "/login",
//     element: <Login />,
//   },
// ]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <Provider store={store}>
    <React.StrictMode>
      <App />
      {/* <Navbar /> */}
      {/* <RouterProvider router={router} /> Use RouterProvider instead of BrowserRouter */}
    </React.StrictMode>
  </Provider>
);
