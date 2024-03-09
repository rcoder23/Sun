import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { logined, logout } from '../../redux/slices/login/index';
// useEffect

import { useAppDispath, useAppSelector } from '../../redux/hooks/index';


// import { useNavigate } from "react-router-dom";
const Login = () => {

  const count = useAppSelector((state) => state.loginr);

  const dispatch = useAppDispath()



  const navigate = useNavigate();
  const [data, setData] = useState({ email: "", password: "" });

  const handleSubmit = async (e) => {
    // let history = useNavigate();
    e.preventDefault();
    axios.post('http://localhost:8081/api/v1/auth/auth', { email: data.email, password: data.password })
      .then(response => {
        console.log(response);
        localStorage.setItem('auth', response.data.id);
        dispatch(logined())
        navigate("/");
      })
      .catch(error => {
        alert('login Failed');
        dispatch(logout())
        console.log(error);
      });
  };

  const onChange = (e) => {
    const { name, value } = e.target;
    setData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  return (
    <div className="w-full max-w-xs">
      <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-2">
            Username
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="email"
            type="email"
            placeholder="Username"
            value={data.email}
            name="email"
            onChange={onChange}
          />
        </div>
        <div className="mb-6">
          <label className="block text-gray-700 text-sm font-bold mb-2">
            Password
          </label>
          <input
            className="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
            id="password"
            type="password"
            value={data.password}
            name="password"
            onChange={onChange}
          />
          <p className="text-red-500 text-xs italic">Please choose a password.</p>
        </div>
        <div className="flex items-center justify-between">
          <button
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit"
          >
            Login
          </button>
          <a
            className="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800"
            href="#"
          >
            Forgot Password?
          </a>
        </div>
      </form>
    </div>
  );
};

// export default Login;

export default Login
// import './Loginn.css';
// import { useState } from 'react';
// import { Link, useNavigate } from 'react-router-dom'
// export const Login = () => {
//   let history = useNavigate();
//   const [credit, setcredit] = useState({ email: "", password: "" })

//   const handleSubmit = async (e) => {

//     e.preventDefault();
//     console.log(credit);
//     const response = await fetch("http://localhost:5000/api/auth/login", {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json'
//       },
//       body: JSON.stringify({ email: credit.email, password: credit.password })
//     });
//     const json = await response.json()
//     console.log(json);

//     if (json.success === true) {
//       localStorage.setItem('auth-token', json.authtoken);
//       // toast.success('🦄 Login Successfully ', {
//       //   position: "top-right",
//       //   autoClose: 5000,
//       //   hideProgressBar: false,
//       //   closeOnClick: true,
//       //   pauseOnHover: true,
//       //   draggable: true,
//       //   progress: undefined,
//       // });

//       setTimeout(function () {
//         history('/');
//       }, 3000);


//     } else {
//       // toast.error('🦄 Email or Password Not Matched', {
//       //   position: "top-right",
//       //   autoClose: 5000,
//       //   hideProgressBar: false,
//       //   closeOnClick: true,
//       //   pauseOnHover: true,
//       //   draggable: true,
//       //   progress: undefined,
//       // });

//       console.log("INVALID CREDITEDIALS")
//     }


//   }

//   const onchange = (e) => {
//     console.log(e.target.value);
//     setcredit({ ...credit, [e.target.name]: e.target.value })
//   }


//   return (
//     <>
//       {/* <ToastContainer
//         position="top-right"
//         autoClose={5000}
//         hideProgressBar={false}
//         newestOnTop={false}
//         closeOnClick
//         rtl={false}
//         pauseOnFocusLoss
//         draggable
//         pauseOnHover
//       /> */}

//       <div className="container1">
//         <center> <h1>Login</h1> </center>
//         <form onSubmit={handleSubmit}>
//           <div className="mb-3">

//             <label className="form-label">
//               Email address
//             </label>
//             <input
//               type="email"
//               className="form-control"
//               name="email"
//               value={credit.email}
//               onChange={onchange}

//               id="email"
//               aria-describedby="emailHelp"

//             />
//           </div>
//           <div className="mb-3">
//             <label className="form-label">
//               Password
//             </label>
//             <input
//               type="password"
//               name="password"
//               onChange={onchange}
//               className="form-control"
//               id="password"
//             />
//           </div>
//           <center><p><Link to="/createUser">Or register now</Link></p></center>
//           <center><button type="submit" className="btn btn-primary">
//             Submit
//           </button>
//           </center>

//         </form>
//       </div>
//     </>
//   )
// }
// export default Login