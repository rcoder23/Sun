import { Pie } from 'react-chartjs-2';
import 'chart.js/auto';
import './Profile.css'
import Card from './Card';
const Profile = () => {
  const data = {
    labels: ['Assigned', 'Pending', 'Process', 'Done', 'Bug', 'Priority'],
    datasets: [
      {
        label: 'My First dataset',

        data: [6, 5, 8, 8, 5, 5, 4]
      }
    ]
  };

  const userDetails = {
    name: 'John Doe',
    email: 'john@example.com',
    project: 'Project X',
    designation: 'Dev',
    role: 'Developer',
    tickets: ['Ticket #1', 'Ticket #2', 'Ticket #3']
  };

  return (
    <>
      <h1>This is proifle page</h1>
      <div className="Details">
        <div className="chartProfile">
          <Card {...userDetails} />
        </div>
        <div className='chartProfile'>
          <Pie data={data} />
        </div>
      </div>

    </>
  )
}

export default Profile
