// import React from 'react';

const Card = ({ name, email, project, role, tickets }) => {
  return (
    <div className="card">
      <div>
        <h2>Name: {name}</h2>
        <p>Email: {email}</p>
        <p>Project: {project}</p>
        <p>Role: {role}</p>
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
  );
};

export default Card;
