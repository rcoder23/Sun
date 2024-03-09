// import React from 'react';
import './Card.css'; // Import the CSS file
import { FaUser } from "react-icons/fa";
import { MdEmail } from "react-icons/md";
import toast, { Toaster } from 'react-hot-toast';
const notify = () => toast('Here is your toast.');

const Card = ({ name, email, project, designation, role, tickets }) => {
  return (
    <>
      <div className="card">
        <div>
          <table>
            <tr>
              <td>
                <FaUser />
              </td>
              <td>
                &nbsp; {name}
              </td>
            </tr>

            <tr>
              <td>
                <MdEmail />
              </td>
              <td>
                &nbsp; {email}
              </td>
            </tr>

            <tr>
              <td>

              </td>
              <td>
                &nbsp; {project}
              </td>
            </tr>

            <tr>
              <td>

              </td>
              <td>
                &nbsp; {designation}
              </td>
            </tr>
          </table>
        </div>
        <div>
          <h3>List of Tickets Assigned:</h3>
          <ul>
            {tickets.map((ticket, index) => (
              <li key={index}>{ticket}</li>
            ))}
          </ul>
        </div>
      </div>
      <button onClick={notify}>Make me a toast</button>
      <Toaster />
    </>
  );
};

export default Card;
