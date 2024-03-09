
import React, { useState, useEffect } from 'react';
import './Home.css';
import axios from "axios";
import { useAppSelector } from '../redux/hooks/index';

import toast, { Toaster } from 'react-hot-toast';


const TicketBoard = () => {


  const [tickets, setTickets] = useState([]);
  const isLogin = useAppSelector((state) => state.loginr);


  useEffect(() => {
    toast.success("this is sucess");
    if (isLogin) {
      const id = localStorage.getItem('auth');
      axios.get('http://localhost:8081/api/v1/auth/getuser/' + id)
        .then(response => {
          console.log(response);
          console.log(response.data.userAssignTickets); // show data
          const ticketIds = response.data.userAssignTickets;
          // Use Promise.all() to handle multiple asynchronous requests
          Promise.all(ticketIds.map(ticketId => axios.get(`http://localhost:8081/api/v1/ticket/getById/${ticketId}`)))
            .then(ticketResponses => {
              const fetchedTickets = ticketResponses.map(ticketResponse => ticketResponse.data);
              console.log("Fetched Tickets:", fetchedTickets);
              setTickets([...fetchedTickets]); // 
            })
            .catch(error => {
              console.error("Error fetching tickets by ID:", error);
            });
        })
        .catch(error => {
          console.error("Error fetching user data:", error);
        });
    } else {
      console.log("login first");
    }
  }, [isLogin]);

  const allowDrop = (ev) => {
    { notify }
    ev.preventDefault();
  };

  const drag = (ev, ticketId) => {
    ev.dataTransfer.setData("text", ticketId.toString());
  };

  const drop = (ev) => {
    console.log(ev.dataTransfer.getData("text"));
    ev.preventDefault();
    const ticketId = parseInt(ev.dataTransfer.getData("text"));
    const updatedTickets = tickets.map(ticket => {
      if (ticket.id === ticketId) {
        return { ...ticket, status: ev.target.id };
      }
      return ticket;
    });
    console.log(ev.target.id);// need to call api here 

    if (!ev.target.id) {

      notify("can't move ticket to invalid place ..");
      return; // If so, do nothing and exit the function
    }

    const status = ev.target.id;

    axios.post('http://localhost:8081/api/v1/ticket/update/' + ticketId + "/" + status)
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });

    setTickets(updatedTickets);
  };

  return (

    <div>

      <Toaster />

      <p>Drag the tickets into the appropriate column:</p>
      <Toaster />

      <div id="PRIORITY" className="ticketColumn" onDrop={drop} onDragOver={allowDrop}>
        <h3 className='title'>Priorities</h3>
        {tickets
          .filter(ticket => ticket.status === "PRIORITY")
          .map(ticket => (
            <div className="progess ticket" key={ticket.id} draggable onDragStart={(e) => drag(e, ticket.id)}>
              {ticket.title} {ticket.categroy}
            </div>
          ))}
      </div>
      <div id="READY" className="ticketColumn" onDrop={drop} onDragOver={allowDrop}>
        <h3 className='title'>Ready for dev</h3>
        {tickets
          .filter(ticket => ticket.status === "READY")
          .map(ticket => (
            <div className="ready ticket" key={ticket.id} draggable onDragStart={(e) => drag(e, ticket.id)}>
              {ticket.title} {ticket.categroy}
            </div>
          ))}
      </div>
      <div id="PROGRESS" className="ticketColumn" onDrop={drop} onDragOver={allowDrop}>
        <h3 className='title'>Ready to Test</h3>
        {tickets
          .filter(ticket => ticket.status === "PROGRESS")
          .map(ticket => (
            <div className="progess ticket" key={ticket.id} draggable onDragStart={(e) => drag(e, ticket.id)}>
              {ticket.title} {ticket.categroy}
            </div>
          ))}
      </div>

      <div id="REVIEW" className="ticketColumn" onDrop={drop} onDragOver={allowDrop}>
        <h3 className='title'>Review </h3>
        {tickets
          .filter(ticket => ticket.status === "REVIEW")
          .map(ticket => (
            <div className="review ticket" key={ticket.id} draggable onDragStart={(e) => drag(e, ticket.id)}>
              {ticket.title} {ticket.categroy}
            </div>
          ))}
      </div>

      <div id="TESTING" className="ticketColumn" onDrop={drop} onDragOver={allowDrop}>
        <h3 className='title' >Testing</h3>
        {tickets
          .filter(ticket => ticket.status === "TESTING")
          .map(ticket => (
            <div className="testing ticket" key={ticket.id} draggable onDragStart={(e) => drag(e, ticket.id)}>
              {ticket.title} {ticket.categroy}
            </div>
          ))}
      </div>

      <div id="FINISHED" className="ticketColumn" onDrop={drop} onDragOver={allowDrop}>
        <h3 className='title' >Done</h3>
        {tickets
          .filter(ticket => ticket.status === "FINISHED")
          .map(ticket => (
            <div className="done ticket" key={ticket.id} draggable onDragStart={(e) => drag(e, ticket.id)}>
              {ticket.title} {ticket.categroy}
            </div>
          ))}
      </div>
    </div>
  );
};

export default TicketBoard;
